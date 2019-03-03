package com.djam.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.djam.game.map.Map;

public abstract class EntityNpcItem extends Entity {

    public EntityNpcItem(Map map, Vector2 position) {
        super(map, position);
    }

}
