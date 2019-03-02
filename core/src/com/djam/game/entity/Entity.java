package com.djam.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.map.Map;

public abstract class Entity {

    private Map map;

    private Vector2 position;

    private Animation animation;

    public Entity(Map map, Vector2 position) {
        this.map = map;
        this.position = position;
        this.animation = this.setupAnimation();
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.animation.render(batch, camera, this.getPosition());
    }

    public void update(OrthographicCamera camera) {
        this.animation.update(camera);
    }

    public boolean move(Direction direction, float speed) {
        Vector2 velocity = new Vector2();

        switch(direction) {
            case LEFT:
                velocity.set(-speed, 0);
                break;
            case RIGHT:
                velocity.set(speed, 0);
                break;
        }

        this.getPosition().add(velocity.x * Gdx.graphics.getDeltaTime(), velocity.y * Gdx.graphics.getDeltaTime());

        return true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public abstract Animation setupAnimation();

}
