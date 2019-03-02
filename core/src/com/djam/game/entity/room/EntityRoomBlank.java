package com.djam.game.entity.room;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

public class EntityRoomBlank extends EntityRoom {

    public EntityRoomBlank(Map map, Vector2 position) {
        super(map, position);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("building/room_invisible.png");

        return animation;
    }
}