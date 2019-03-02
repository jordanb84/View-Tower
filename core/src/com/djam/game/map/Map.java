package com.djam.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.djam.game.entity.Direction;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityLiving;
import com.djam.game.entity.room.Business;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Entity> entities = new ArrayList<Entity>();

    private List<Entity> entitySpawnQueue = new ArrayList<Entity>();
    private List<Entity> entityDespawnQueue = new ArrayList<Entity>();

    private Business business;

    private List<Rectangle> collisionBodies = new ArrayList<Rectangle>();

    public Map() {
        this.business = new Business(this);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.business.render(batch, camera);

        for(Entity entity : this.getEntities()) {
            entity.render(batch, camera);
        }
    }

    public void update(OrthographicCamera camera) {
        this.business.update(camera);
        this.applyGravity();

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

    public void applyGravity() {
        for(Entity entity : this.getEntities()) {
            if(entity instanceof EntityLiving) {
                entity.move(Direction.DOWN, 10);
            }
        }
    }

    public List<Rectangle> getCollisionBodies() {
        return collisionBodies;
    }

    public void addCollisionBody(Rectangle body) {
        this.collisionBodies.add(body);
    }

    public boolean collisionAt(Rectangle rectangle) {
        for(Rectangle collisionBody : this.getCollisionBodies()) {
            if(rectangle.overlaps(collisionBody)) {
                return true;
            }
        }

        return false;
    }

}
