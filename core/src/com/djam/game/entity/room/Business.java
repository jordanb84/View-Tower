package com.djam.game.entity.room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.djam.game.assets.Assets;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Business {

    private List<EntityRoom> rooms = new ArrayList<EntityRoom>();

    private boolean placing;

    //TODO probably switch out to BuildingType to support placing multiple types of rooms
    private Sprite placingSprite;

    private Vector2 mousePosition = new Vector2();

    private Map map;

    private NpcType placingNpc;

    public Business(Map map) {
        this.map = map;

        this.placing = true;

        this.placingSprite = new Sprite(Assets.getInstance().getTexture("building/room.png"));
        this.placingSprite.setAlpha(0.5f);

        this.generateRoomGrid(3, 3);

        //this.placingNpc = NpcType.Farmer;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(EntityRoom room : this.rooms) {
            room.render(batch, camera);
        }

        //TODO render red overlay if you can't place it, possibly green if you can
        if(this.placing) {
            Rectangle mouseBody = new Rectangle(this.mousePosition.x, this.mousePosition.y, 0, 0);

            List<EntityRoom> roomRemoveQueue = new ArrayList<EntityRoom>();
            List<EntityRoom> roomAddQueue = new ArrayList<EntityRoom>();

            //Rooms are NOT kept based around the original index in relation to position as generated

            for(EntityRoom room : this.rooms) {
                if(room instanceof EntityRoomBlank) {
                    if(mouseBody.overlaps(room.getBody())) {
                        this.placingSprite.setPosition(room.getPosition().x, room.getPosition().y);
                        this.placingSprite.draw(batch);

                        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                            roomRemoveQueue.add(room);
                            roomAddQueue.add(new EntityRoom(this.map, new Vector2(room.getPosition())));
                        }
                    }
                } else {
                    if(this.isPlacingNpc()) {
                        for (EntityDesk desk : room.getDesks()) {
                            //TODO Highlight chair, show faded placing NPC behind desk
                            if (mouseBody.overlaps(desk.getBody())) {
                                if (!desk.hasNpc()) {
                                    desk.placing(this.placingNpc);

                                    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                        if(this.map.getCurrency().getBalance() >= this.placingNpc.COST) {
                                            desk.placeNpc(this.placingNpc);

                                            this.map.getCurrency().modifyBalance(-this.placingNpc.COST);
                                        }
                                    }
                                }
                            } else {
                                desk.unplace();
                            }
                        }
                    }
                }
            }

            this.rooms.removeAll(roomRemoveQueue);
            this.rooms.addAll(roomAddQueue);

            if(this.isPlacingNpc()) {
                this.placingNpc.SPRITE.setScale(0.5f);
                this.placingNpc.SPRITE.setPosition(this.mousePosition.x, this.mousePosition.y - this.placingNpc.SPRITE.getHeight() / 2);
                this.placingNpc.SPRITE.draw(batch);
                this.placingNpc.SPRITE.setScale(1);

                if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    this.placingNpc = null;
                }
            }
        }
    }

    public void update(OrthographicCamera camera) {
        for(EntityRoom room : this.rooms) {
            room.update(camera);
        }

        Vector3 mouse = new Vector3();
        mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);

        camera.unproject(mouse);

        this.mousePosition.set(mouse.x, mouse.y);
    }

    public void generateRoomGrid(int gridWidth, int gridHeight) {
        float roomWidth = this.placingSprite.getWidth();
        float roomHeight = this.placingSprite.getHeight();

        for(int y = 0; y < gridHeight; y++) {
            for(int x = 0; x < gridWidth; x++) {
                Vector2 roomPosition = new Vector2(x * roomWidth, y * roomHeight);
                this.rooms.add(new EntityRoomBlank(this.map, roomPosition));
            }
        }
    }

    public boolean isPlacingNpc() {
        return this.placingNpc != null;
    }

    public void startPlacingNpc(NpcType type) {
        this.placingNpc = type;
        this.placing = true;
    }

}
