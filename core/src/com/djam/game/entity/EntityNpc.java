package com.djam.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.entity.impl.EntityDecor;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;

public abstract class EntityNpc extends Entity {

    private float happiness;

    private EntityRoom room;

    private float elapsedSinceHappinessChange;

    private float happinessChangeInterval = 3;

    public EntityNpc(EntityRoom room, Map map, Vector2 position) {
        super(map, position);
        this.happiness = 100;
        this.room = room;
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        this.elapsedSinceHappinessChange += 1 * Gdx.graphics.getDeltaTime();

        if(this.elapsedSinceHappinessChange >= this.happinessChangeInterval) {
            this.elapsedSinceHappinessChange = 0;
            this.happinessChange();
        }
    }

    public EntityRoom getRoom() {
        return room;
    }

    public void happinessChange() {
        this.happiness -= 0.3f;

        for(EntityDecor decor : this.getRoom().getDecor()) {
            this.happiness += decor.getDecorType().HAPPINESS_BONUS;
        }

        if(this.happiness > 100) {
            this.happiness = 100;
        }

        if(this.happiness < 40) {
            this.happiness = 40;
        }
    }

    public float getHappiness() {
        return happiness;
    }

}
