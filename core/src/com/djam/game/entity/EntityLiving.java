package com.djam.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.animation.DirectionalAnimation;
import com.djam.game.map.Map;

public abstract class EntityLiving extends Entity {

    private DirectionalAnimation directionalAnimation;

    private Direction direction;

    public EntityLiving(Map map, Vector2 position) {
        super(map, position);
        this.direction = Direction.RIGHT;
        //this.directionalAnimation = new DirectionalAnimation(Direction.LEFT);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.directionalAnimation.render(batch, camera, this.getPosition());
    }

    @Override
    public void update(OrthographicCamera camera) {
        this.updatePhysics();
        this.directionalAnimation.update(camera);
    }

    public void addDirectionalAnimation(Direction direction, Animation animation) {
        this.directionalAnimation.addAnimation(direction, animation);
    }

    public DirectionalAnimation getDirectionalAnimation() {
        return directionalAnimation;
    }

    public void setDirectionalAnimation(DirectionalAnimation directionalAnimation) {
        this.directionalAnimation = directionalAnimation;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean move(Direction direction, float speed) {
        this.setDirection(direction);
        return super.move(direction, speed);
    }
}
