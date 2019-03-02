package com.djam.game.animation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.entity.Direction;

import java.util.HashMap;

public class DirectionalAnimation {

    public HashMap<Direction, Animation> animations = new HashMap<Direction, Animation>();

    private Direction direction;

    public DirectionalAnimation(Direction startDirection) {
        this.direction = startDirection;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera, Vector2 position) {
        this.getCurrentAnimation().render(batch, camera, position);
    }

    public void update(OrthographicCamera camera) {
        this.getCurrentAnimation().update(camera);
    }

    public void addAnimation(Direction direction, Animation animation) {
        this.animations.put(direction, animation);
    }

    public Animation getCurrentAnimation() {
        System.out.println("Getting for direction " + this.direction);

        for(HashMap.Entry<Direction, Animation> entry : this.animations.entrySet()) {
            System.out.println(entry.getKey() + " is " + entry.getValue());
        }
        return this.animations.get(this.direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
