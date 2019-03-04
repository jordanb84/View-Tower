package com.djam.game.map;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.djam.game.economy.Currency;
import com.djam.game.economy.Research;
import com.djam.game.entity.Direction;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityLiving;
import com.djam.game.entity.impl.EntityLadder;
import com.djam.game.entity.impl.EntityPlayer;
import com.djam.game.entity.room.Business;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.entity.room.EntityRoomBlank;
import com.djam.game.happiness.Happiness;
import com.djam.game.ui.text.Text;
import com.djam.game.ui.text.TextType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

    private Background background;

    private Research research;

    private RayHandler rayHandler;

    private World world;

    private HashMap<String, Vector2> text = new HashMap<String, Vector2>();

    private float profit;

    public Map() {
        this.business = new Business(this);
        this.currency = new Currency();

        this.textCamera = new OrthographicCamera();
        this.textCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.happiness = new Happiness(this);

        this.background = new Background();

        //EntityPlayer player = new EntityPlayer(this, new Vector2(377, 30));
        EntityPlayer player = new EntityPlayer(this, new Vector2(377, 10));

        this.spawn(player);

        player.updateBody();

        System.out.println("Player set to " + player.getBody().getX() + "/" + player.getBody().getY() + "/" + player.getBody().getWidth() + "/" + player.getBody().getHeight());

        EntityRoomBlank room = this.business.getEmptyRoomAt(player.getBody());

        room.setupRoom();

        System.out.println("Room: " + room);

        this.business.replaceRoom(room);

        this.research = new Research();

        this.world = new World(new Vector2(0, 0), false);
        this.rayHandler = new RayHandler(world);

        RayHandler.useDiffuseLight(true);

        this.rayHandler.setAmbientLight(Color.GRAY);

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        this.rayHandler.setAmbientLight(Color.GRAY);
        rayHandler.setCulling(true);
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);
        rayHandler.setShadows(true);

        //new PointLight(rayHandler, 100, new Color(0,1,0,1), 100, 377, 30);

    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.background.render(batch, camera);

        this.business.render(batch, camera);

        for(Entity entity : this.getEntities()) {
            entity.render(batch, camera);
        }

    }

    public void renderLights(SpriteBatch batch, OrthographicCamera camera) {
        batch.end();
        this.rayHandler.setCombinedMatrix(camera.combined);
        this.rayHandler.updateAndRender();
        batch.begin();
    }

    public void renderHud(SpriteBatch batch, OrthographicCamera camera) {
        this.currency.render(this, batch, camera);
        this.research.render(batch, camera);

        this.happiness.render(batch, camera);


    }

    public void renderText(SpriteBatch batch, OrthographicCamera camera) {
        if(this.text.size() > 0) {
            //batch.setProjectionMatrix(this.getTextCamera().combined);
            for(HashMap.Entry<String, Vector2> entry : this.text.entrySet()) {
                TextType.Default_Medium.FONT.draw(batch, entry.getKey(), entry.getValue().x, entry.getValue().y);
            }

            this.text.clear();
        }
    }

    public void update(OrthographicCamera camera) {
        this.profit = 0;
        this.business.update(camera);
        this.applyGravity();

        this.currency.update(camera);
        this.research.update(camera);

        this.getEntities().addAll(this.entitySpawnQueue);
        this.getEntities().removeAll(this.entityDespawnQueue);

        this.entitySpawnQueue.clear();
        this.entityDespawnQueue.clear();

        for(Entity entity : this.getEntities()) {
            entity.update(camera);
        }

        this.happiness.update(camera);

        this.profit = 0;

        for(EntityRoom room : this.business.getRooms()) {
            profit += room.getProfit();
        }

        String formattedValue = new DecimalFormat("#.##").format(this.profit);

        System.out.println("Profit " + formattedValue);
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

        return this.business.emptyRoomAt(rectangle);
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

    public Research getResearch() {
        return research;
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public float getAverageHappiness() {
        return this.getHappiness().getPercentage() / 100;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public void drawText(String text, Vector2 position) {
        this.text.put(text, position);
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

}
