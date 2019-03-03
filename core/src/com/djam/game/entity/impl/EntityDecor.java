package com.djam.game.entity.impl;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

import java.util.Random;

public class EntityDecor extends Entity {

    private DecorType decorType;

    public EntityDecor(DecorType decorType, Map map, Vector2 position) {
        super(map, position);
        Animation animation = new Animation(1);

        Random spriteRandom = new Random();

        if(decorType.SPRITES.size() > 1) {
            animation.addFrame(decorType.SPRITES.get(spriteRandom.nextInt(decorType.SPRITES.size())));
        } else {
            animation.addFrame(decorType.SPRITES.get(0));
        }

        this.setAnimation(animation);

        this.decorType = decorType;
    }

    @Override
    public Animation setupAnimation() {
        return null;
    }

    public DecorType getDecorType() {
        return decorType;
    }

}
