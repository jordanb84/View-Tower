package com.djam.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.djam.game.economy.Currency;
import com.djam.game.entity.Direction;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityLiving;
import com.djam.game.entity.impl.EntityLadder;
import com.djam.game.entity.room.Business;
import com.djam.game.happiness.Happiness;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private List<Entity> entities = new ArrayList<Entity>();

    private List<Entity> entitySpawnQueue = new ArrayList<Entity>();
    private List<Entity> entityDespawnQueue = new ArrayList<Entity>();

    private Business business;

    private List<Rectangle> collisionBodies = new ArrayList<Rectangle>();

    private Currency currency;

    private List<EntityLadder> ladders = new ArrayList<EntityLadder>();

    private OrthographicCamera textCamera;

    private Happiness happiness;

    public Map() {
        this.business = new Business(this);
        this.currency = new Currency();

        this.textCamera = new OrthographicCamera();
        this.textCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.happiness = new Happiness(this);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.business.render(batch, camera);

        for(Entity entity : this.getEntities()) {
            entity.render(batch, camera);
        }

        this.currency.render(batch, camera);

        this.happiness.render(batch, camera);
    }

    public void update(OrthographicCamera camera) {
        this.business.update(camera);
        this.applyGravity();

        this.currency.update(camera);

        this.getEntities().addAll(this.entitySpawnQueue);
        this.getEntities().removeAll(this.entityDespawnQueue);

        this.entitySpawnQueue.clear();
        this.entityDespawnQueue.clear();

        for(Entity entity : this.getEntities()) {
            entity.update(camera);
        }

        this.happiness.update(camera);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void spawn(Entity entity) {
        this.entitySpawnQueue.add(entity);

        if(entity instanceof EntityLadder) {
            //Keeping ladders to separate list to ease performance drain from entity check loops
            this.ladders.add(((EntityLadder) entity));
        }
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

    public Business getBusiness() {
        return business;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<EntityLadder> getLadders() {
        return ladders;
    }

    public OrthographicCamera getTextCamera() {
        return textCamera;
    }

}
