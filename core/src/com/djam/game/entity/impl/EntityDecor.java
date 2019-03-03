package com.djam.game.entity.impl;

import box2dLight.PointLight;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

import java.util.Random;

public class EntityDecor extends Entity {

    private DecorType decorType;

    public EntityDecor(DecorType decorType, Map map, Vector2 position) {
        super(map, position);
        Animation animation = new Animation(0.1f);

        Random spriteRandom = new Random();

        if(!decorType.ANIMATED) {
            if (decorType.SPRITES.size() > 1) {
                animation.addFrame(decorType.SPRITES.get(spriteRandom.nextInt(decorType.SPRITES.size())));
            } else {
                animation.addFrame(decorType.SPRITES.get(0));
            }
        } else {
            for(String spritePath : decorType.SPRITES) {
                animation.addFrame(spritePath);
            }
        }

        System.out.println("Generated decor " + decorType.name() + " with " + decorType.SPRITES.size() + " sprites");

        this.setAnimation(animation);

        this.decorType = decorType;

        if(this.decorType.LIGHT_COLOR != null) {
            new PointLight(map.getRayHandler(), 100, this.decorType.LIGHT_COLOR, 30, this.getPosition().x + this.getWidth() / 2, this.getPosition().y + this.getHeight() / 2);
        }
    }

    @Override
    public Animation setupAnimation() {
        return null;
    }

    public DecorType getDecorType() {
        return decorType;
    }

}
