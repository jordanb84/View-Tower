package com.djam.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.impl.EntityDecor;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;
import com.djam.game.ui.text.TextType;

import java.util.Random;

public abstract class EntityNpc extends Entity {

    private float happiness;

    private EntityRoom room;

    private float elapsedSinceHappinessChange;

    private float happinessChangeInterval = 4;

    public EntityNpc(EntityRoom room, Map map, Vector2 position) {
        super(map, position);
        this.happiness = 100;
        this.room = room;
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        this.elapsedSinceHappinessChange += 1 * Gdx.graphics.getDeltaTime();

        if(this.elapsedSinceHappinessChange >= this.happinessChangeInterval) {
            this.elapsedSinceHappinessChange = 0;
            this.happinessChange();
        }
    }

    public EntityRoom getRoom() {
        return room;
    }

    public void happinessChange() {
        float happinessChange = -1.6f;

        for(EntityDecor decor : this.getRoom().getDecor()) {
            happinessChange += decor.getDecorType().HAPPINESS_BONUS;
        }

        if(this.happiness > 100) {
            this.happiness = 100;
        }

        if(this.happiness < 40) {
            this.happiness = 40;
        }

        this.happiness += happinessChange;

        Vector2 position = new Vector2(this.getPosition().x - 16, this.getPosition().y + 8);
        this.getMap().spawn(new EntityHeart(this.getMap(), position, happinessChange));
    }

    public float getHappiness() {
        return happiness;
    }

}

class EntityHeart extends Entity {

    private boolean movingUp;

    private float elapsedSinceSpawn;

    private float lifespan;

    private float speed = 60;

    private float value;

    public EntityHeart(Map map, Vector2 position, float value) {
        super(map, position);
        this.movingUp = true;
        this.lifespan = new Random().nextInt(2);

        if(this.lifespan < 1) {
            this.lifespan = 1;
        }

        this.value = value;

    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("ui/heart_icon.png");

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

        String changeSymbol = ("+");

        if(this.value < 0) {
            changeSymbol = "-";
        }

        //System.out.println("Change " + this.value);

        float floatData = Math.abs(this.value * 10);
        int data = (int) floatData;

        if(value > 0) {
            TextType.Default_Medium.FONT.draw(batch, "<3", this.getPosition().x + 16, this.getPosition().y + 12);
        } else {
            TextType.Default_Medium.FONT.draw(batch, changeSymbol + data, this.getPosition().x + 16, this.getPosition().y + 12);
        }
    }
}