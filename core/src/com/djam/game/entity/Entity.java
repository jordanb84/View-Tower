package com.djam.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.map.Map;

public abstract class Entity {

    private Map map;

    private Vector2 position;

    private Animation animation;

    private boolean moving;

    private Rectangle body = new Rectangle();

    public Entity(Map map, Vector2 position) {
        this.map = map;
        this.position = position;
        this.animation = this.setupAnimation();
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.animation.render(batch, camera, this.getPosition());
    }

    public void update(OrthographicCamera camera) {
        this.updatePhysics();
        this.animation.update(camera);
    }

    public void updatePhysics() {
        this.moving = false;
        this.updateBody();
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
            case UP:
                velocity.set(0, speed);
                break;
            case DOWN:
                velocity.set(0, -speed);
                break;
        }

        float velocityX = velocity.x * Gdx.graphics.getDeltaTime();
        float velocityY = velocity.y * Gdx.graphics.getDeltaTime();

        Vector2 newPosition = new Vector2(this.getPosition().x + velocityX, this.getPosition().y + velocityY);

        Rectangle newPositionBody = new Rectangle(newPosition.x, newPosition.y, this.getWidth(), this.getHeight());

        boolean collisionAtNewPosition = this.getMap().collisionAt(newPositionBody);

        if(!collisionAtNewPosition) {
            this.getPosition().add(velocity.x * Gdx.graphics.getDeltaTime(), velocity.y * Gdx.graphics.getDeltaTime());

            this.moving = true;
        }

        return collisionAtNewPosition;
    }

    public Vector2 getPosition() {
        return position;
    }

    public abstract Animation setupAnimation();

    public boolean isMoving() {
        return moving;
    }

    public void updateBody() {
        this.getBody().set(this.getPosition().x, this.getPosition().y, this.getWidth(), this.getHeight());
    }

    public float getWidth() {
        return this.animation.getCurrentFrame().getSprite().getWidth();
    }

    public float getHeight() {
        return this.animation.getCurrentFrame().getSprite().getHeight();
    }

    public Rectangle getBody() {
        return body;
    }

    public Map getMap() {
        return map;
    }

}
