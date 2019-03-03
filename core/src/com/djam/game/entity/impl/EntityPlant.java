package com.djam.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityNpcItem;
import com.djam.game.map.Map;
import com.djam.game.ui.text.TextType;

import java.util.Random;

public class EntityPlant extends EntityNpcItem {

    private NpcItemType plantType;

    public EntityPlant(NpcItemType plantType, Map map, Vector2 position) {
        super(map, position);
        this.plantType = plantType;

        float interval = new Random().nextInt(4);

        if(interval < 2) {
            interval = 2;
        }

        Animation animation = new Animation(interval);

        for(String spritePath : this.plantType.STAGE_SPRITE_PATHS) {
            animation.addFrame(spritePath);
        }

        this.setAnimation(animation);
    }

    @Override
    public Animation setupAnimation() {
        return null;
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        if(this.getAnimation().isComplete()) {
            this.getAnimation().setComplete(false);
            this.getMap().spawn(new EntityCoin(this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y), this.plantType.PROFIT));
            this.getMap().getCurrency().modifyBalance(this.plantType.PROFIT);
        }
    }

}

class EntityCoin extends Entity {

    private boolean movingUp;

    private float elapsedSinceSpawn;

    private float lifespan;

    private float speed = 70;

    private int value;

    public EntityCoin(Map map, Vector2 position, int value) {
        super(map, position);
        this.movingUp = true;
        this.lifespan = new Random().nextInt(3);

        if(this.lifespan < 2) {
            this.lifespan = 2;
        }

        this.value = value;

    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("ui/coin_large.png");

        return animation;
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        if(this.movingUp) {
            this.getPosition().add(0, speed * Gdx.graphics.getDeltaTime());

            this.elapsedSinceSpawn += 1 * Gdx.graphics.getDeltaTime();

            if(this.elapsedSinceSpawn >= this.lifespan) {
                this.movingUp = false;
            }
        } else {
            this.getMap().despawn(this);
        }
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        super.render(batch, camera);
        OrthographicCamera textCamera = this.getMap().getTextCamera();

        //Vector2 textPosition = new Vector2(this.getPosition().x + camera.position.x + 220, this.getPosition().y + camera.position.y + 160);

       // batch.setProjectionMatrix(textCamera.combined);
        TextType.Default_Medium.FONT.draw(batch, "+" + this.value, this.getPosition().x + 16, this.getPosition().y + 12);
    }
}