package com.djam.game.entity.impl;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityNpc;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;

public class EntityFarmer extends EntityNpc {

    public EntityFarmer(EntityRoom room, Map map, Vector2 position) {
        super(room, map, position);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(0.2f);

        animation.addFrame("npc/farmer_0.png");
        animation.addFrame("npc/farmer_1.png");
        animation.addFrame("npc/farmer_2.png");
        animation.addFrame("npc/farmer_3.png");
        animation.addFrame("npc/farmer_4.png");
        animation.addFrame("npc/farmer_5.png");

        return animation;
    }
}
