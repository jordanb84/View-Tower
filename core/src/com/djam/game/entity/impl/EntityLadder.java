package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

public class EntityLadder extends Entity {

    public EntityLadder(Map map, Vector2 position) {
        super(map, position);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("building/ladder.png");

        return animation;
    }

}
