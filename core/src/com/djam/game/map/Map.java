package com.djam.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.djam.game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Entity> entities = new ArrayList<Entity>();

    private List<Entity> entitySpawnQueue = new ArrayList<Entity>();
    private List<Entity> entityDespawnQueue = new ArrayList<Entity>();

    public Map() {

    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(Entity entity : this.getEntities()) {
            entity.render(batch, camera);
        }
    }

    public void update(OrthographicCamera camera) {
        this.getEntities().addAll(this.entitySpawnQueue);
        this.getEntities().removeAll(this.entityDespawnQueue);

        this.entitySpawnQueue.clear();
        this.entityDespawnQueue.clear();

        for(Entity entity : this.getEntities()) {
            entity.update(camera);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void spawn(Entity entity) {
        this.entitySpawnQueue.add(entity);
    }

    public void despawn(Entity entity) {
        this.entityDespawnQueue.add(entity);
    }

}
