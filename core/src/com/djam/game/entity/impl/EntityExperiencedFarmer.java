package com.djam.game.entity.impl;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;

public class EntityExperiencedFarmer extends EntityFarmer {

    public EntityExperiencedFarmer(EntityRoom room, Map map, Vector2 position) {
        super(room, map, position);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(0.2f);

        animation.addFrame("npc/senior_farmer_0.png");
        animation.addFrame("npc/senior_farmer_1.png");
        animation.addFrame("npc/senior_farmer_2.png");
        animation.addFrame("npc/senior_farmer_3.png");
        animation.addFrame("npc/senior_farmer_4.png");
        animation.addFrame("npc/senior_farmer_5.png");
        return animation;
    }
}
