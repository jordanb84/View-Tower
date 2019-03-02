package com.djam.game.entity.impl;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

import java.util.Random;

public class EntityPlant extends Entity {

    private PlantType plantType;

    public EntityPlant(PlantType plantType, Map map, Vector2 position) {
        super(map, position);
        this.plantType = plantType;

        float interval = new Random().nextInt(4);

        if(interval < 2) {
            interval = 2;
        }

        Animation animation = new Animation(interval);

        for(String spritePath : this.plantType.STAGE_SPRITE_PATHS) {
            animation.addFrame(spritePath);
        }

        this.setAnimation(animation);
    }

    @Override
    public Animation setupAnimation() {
        return null;
    }
}
