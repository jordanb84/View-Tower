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

public class EntityBookshelf extends EntityNpcItem {

    private NpcItemType itemType;

    public EntityBookshelf(NpcItemType itemType, Map map, Vector2 position) {
        super(map, position);
        this.itemType = itemType;

        float interval = new Random().nextInt(4);

        if(interval < 2) {
            interval = 2;
        }

        Animation animation = new Animation(interval);

        for(String spritePath : this.itemType.STAGE_SPRITE_PATHS) {
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
            this.getMap().spawn(new EntityResearch(this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y), this.itemType.PROFIT));
            this.getMap().getResearch().modifyBalance(this.itemType.PROFIT);
        }
    }

}

class EntityResearch extends Entity {

    private boolean movingUp;

    private float elapsedSinceSpawn;

    private float lifespan;

    private float speed = 60;

    private int value;

    public EntityResearch(Map map, Vector2 position, int value) {
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

        animation.addFrame("ui/research_large.png");

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
        TextType.Default_Small.FONT.draw(batch, "+" + this.value, this.getPosition().x + 12, this.getPosition().y + 12);
    }
}