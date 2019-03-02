package com.djam.game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.animation.DirectionalAnimation;
import com.djam.game.map.Map;

public abstract class EntityLiving extends Entity {

    private DirectionalAnimation directionalAnimation;

    public EntityLiving(Map map, Vector2 position) {
        super(map, position);
        this.directionalAnimation = new DirectionalAnimation(Direction.RIGHT);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.directionalAnimation.render(batch, camera, this.getPosition());
    }

    @Override
    public void update(OrthographicCamera camera) {
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

}
