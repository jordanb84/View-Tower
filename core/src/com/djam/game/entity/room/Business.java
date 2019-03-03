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
import com.djam.game.entity.impl.DecorType;
import com.djam.game.entity.impl.EntityDecor;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.map.Map;
import com.djam.game.ui.impl.UnlockTypes;

import java.util.ArrayList;
import java.util.List;

public class Business {

    private List<EntityRoom> rooms = new ArrayList<EntityRoom>();

    private List<BusinessRow> rows = new ArrayList<BusinessRow>();

    private boolean placing;

    //TODO probably switch out to BuildingType to support placing multiple types of rooms
    private Sprite placingSprite;

    private Vector2 mousePosition = new Vector2();

    private Map map;

    private NpcType placingNpc;

    private boolean placingRoom;

    private int roomPrice = 100;

    private boolean placingDecor;

    private DecorType currentDecor;
    private Sprite currentDecorSprite;

    private Sprite roomIcon;

    private boolean release;

    private int gridWidth;

    private List<UnlockTypes> unlocks = new ArrayList<UnlockTypes>();

    private boolean hover;

    public Business(Map map) {
        this.map = map;

        //this.placing = true;

        //this.placingRoom = true;

        this.placingSprite = new Sprite(Assets.getInstance().getTexture("building/room.png"));
        this.placingSprite.setAlpha(0.5f);

        this.roomIcon = new Sprite(Assets.getInstance().getTexture("ui/room_icon.png"));

        this.gridWidth = 3;
        this.generateRoomGrid(this.gridWidth, 2);

        //this.addBlankRow();
        //this.addBlankRow();

        //this.placingNpc = NpcType.Farmer;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(EntityRoom room : this.rooms) {
            room.render(batch, camera);
        }

        //System.out.println("Hovering " + this.isHover());

        //TODO render red overlay if you can't place it, possibly green if you can
        if(this.placing || this.placingRoom || this.placingDecor) {
            Rectangle mouseBody = new Rectangle(this.mousePosition.x, this.mousePosition.y, 0, 0);

            List<EntityRoom> roomRemoveQueue = new ArrayList<EntityRoom>();
            List<EntityRoom> roomAddQueue = new ArrayList<EntityRoom>();

            //Rooms are NOT kept based around the original index in relation to position as generated

            for(EntityRoom room : this.rooms) {
                if(room instanceof EntityRoomBlank) {
                    if(this.placingRoom) {
                        if (mouseBody.overlaps(room.getBody())) {
                            this.placingSprite.setPosition(room.getPosition().x, room.getPosition().y);
                            this.placingSprite.draw(batch);

                            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !(this.isHover())) {
                                if(this.map.getCurrency().getBalance() >= this.roomPrice) {
                                    roomRemoveQueue.add(room);

                                    EntityRoom fullRoom = new EntityRoom(this.map, new Vector2(room.getPosition()));
                                    fullRoom.setupRoom();

                                    roomAddQueue.add(fullRoom);

                                    this.map.getCurrency().modifyBalance(-this.roomPrice);
                                }

                                if(this.map.getCurrency().getBalance() < this.roomPrice) {
                                    this.stopPlacingRoom();
                                }
                            }
                        }
                    }
                } else {
                    if(this.isPlacingNpc()) {
                        for (EntityDesk desk : room.getDesks()) {
                            //TODO Highlight chair, show faded placing NPC behind desk
                            if (mouseBody.overlaps(desk.getBody())) {
                                if (!desk.hasNpc()) {
                                    desk.placing(this.placingNpc);

                                    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !this.isHover()) {
                                        if(this.map.getCurrency().getBalance() >= this.placingNpc.COST) {
                                            desk.placeNpc(this.placingNpc);

                                            this.map.getCurrency().modifyBalance(-this.placingNpc.COST);

                                            desk.unplace();
                                        }

                                        if(this.map.getCurrency().getBalance() <= this.placingNpc.COST) {
                                            this.placingNpc = null;
                                            desk.unplace();
                                        }
                                    }
                                }
                            } else {
                                desk.unplace();
                            }
                        }
                    }

                    if(this.placingDecor) {
                        if(!(room instanceof EntityRoomBlank)) {
                            //TODO use fitsInside to make sure you can only place if the decor fits there
                            if(mouseBody.overlaps(room.getBody())) {
                                //System.out.println("Overlaps decor in room: " + room.bodyOverlapsDecor(mouseBody));

                                Rectangle newBody = new Rectangle(this.mousePosition.x, this.mousePosition.y, this.currentDecorSprite.getWidth(), this.currentDecorSprite.getHeight());
                                if(!room.bodyOverlapsDecor(newBody)) {
                                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !this.isHover()) {
                                        if (this.release) {
                                            if (this.map.getCurrency().getBalance() >= this.currentDecor.PRICE) {
                                                Vector2 position = new Vector2(this.mousePosition.x, this.mousePosition.y);
                                                room.getDecor().add(new EntityDecor(this.currentDecor, this.map, position));

                                                this.map.getCurrency().modifyBalance(-this.currentDecor.PRICE);
                                            }

                                            //System.out.println("Current decor: " + this.currentDecor);

                                            if (this.map.getCurrency().getBalance() <= this.currentDecor.PRICE) {
                                                this.stopPlacing();
                                            }

                                            this.release = false;
                                        }
                                    } else {
                                        this.release = true;
                                    }


                                }
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

            if(this.placingDecor) {
                this.currentDecorSprite.setScale(1f);
                this.currentDecorSprite.setAlpha(0.5f);
                this.currentDecorSprite.setPosition(this.mousePosition.x - this.currentDecorSprite.getWidth() / 6, this.mousePosition.y + this.currentDecorSprite.getHeight() / 6);
                this.currentDecorSprite.draw(batch);
                this.currentDecorSprite.setAlpha(1);
                this.currentDecorSprite.setScale(1);

                if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    this.stopPlacingDecor();
                }
            }

            if(this.placingRoom) {
                this.roomIcon.setScale(0.5f);
                this.roomIcon.setPosition(this.mousePosition.x, this.mousePosition.y - this.roomIcon.getHeight() / 2);
                this.roomIcon.draw(batch);
                this.roomIcon.setScale(1);

                if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                    this.stopPlacingRoom();
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
            List<EntityRoom> rowRooms = new ArrayList<EntityRoom>();

            for(int x = 0; x < gridWidth; x++) {
                Vector2 roomPosition = new Vector2(x * roomWidth, y * roomHeight);
                roomPosition.add(0, 0);
                rowRooms.add(new EntityRoomBlank(this.map, roomPosition));
            }

            this.addRow(rowRooms);
            //this.rooms.addAll(rowRooms);
        }
    }

    public void addRow(List<EntityRoom> rowRooms) {
        this.rows.add(new BusinessRow(rowRooms));

        this.rooms.addAll(rowRooms);
    }

    public void addBlankRow() {
        float roomWidth = this.placingSprite.getWidth();
        float roomHeight = this.placingSprite.getHeight();

        int y = this.getFloors();

        List<EntityRoom> rowRooms = new ArrayList<EntityRoom>();

        for(int x = 0; x < gridWidth; x++) {
            Vector2 roomPosition = new Vector2(x * roomWidth, y * roomHeight);
            rowRooms.add(new EntityRoomBlank(this.map, roomPosition));
        }

        System.out.println("Adding row " + y + " with " + rowRooms.size() + " rooms");
        this.addRow(rowRooms);
    }

    public boolean isPlacingNpc() {
        return this.placingNpc != null;
    }

    public void startPlacingNpc(NpcType type) {
        this.placingNpc = type;
        this.placing = true;
    }

    public void startPlacingRoom() {
        this.placingRoom = true;
    }

    public void startPlacingDecor(DecorType decor) {
        this.placingDecor = true;
        this.currentDecor = decor;
        this.currentDecorSprite = new Sprite(Assets.getInstance().getTexture(decor.SPRITES.get(0)));

        System.out.println("Starting placing, set decor sprite to " + this.currentDecorSprite);
    }

    public void stopPlacingDecor() {
        this.placingDecor = false;
        this.currentDecorSprite = null;
        this.currentDecor = null;
    }

    public void stopPlacingRoom() {
        this.placingRoom = false;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void stopPlacingNpc() {
        this.placingNpc = null;
    }

    public boolean emptyRoomAt(Rectangle rectangle) {
        for(EntityRoom room : this.rooms) {
            if(room instanceof EntityRoomBlank) {
                if (room.getBody().overlaps(rectangle)) {
                    return true;
                }
            }
        }

        return false;
    }

    public EntityRoomBlank getEmptyRoomAt(Rectangle rectangle) {
        for(EntityRoom room : this.rooms) {
            room.updateBody();
            if(room instanceof EntityRoomBlank) {
                if (room.getBody().overlaps(rectangle)) {
                    return ((EntityRoomBlank) room);
                }
            }
        }

        return null;
    }

    public void replaceRoom(EntityRoomBlank room) {
        this.rooms.remove(room);
        this.rooms.add(new EntityRoom(this.map, new Vector2(room.getPosition().x, room.getPosition().y)));
    }

    public List<EntityRoom> getRooms() {
        return rooms;
    }

    public void stopPlacing() {
        this.stopPlacingDecor();
        this.stopPlacingRoom();
        this.stopPlacingNpc();
    }

    public int getFloors() {
        return this.rows.size();
    }

    public Map getMap() {
        return map;
    }

    public List<UnlockTypes> getUnlocks() {
        return unlocks;
    }

    public boolean hasUnlock(UnlockTypes unlock) {
        for(UnlockTypes unlockTypes : this.getUnlocks()) {
            if(unlockTypes == unlock) {
                return true;
            }
        }

        return false;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

}

class BusinessRow { //floor

    List<EntityRoom> rooms = new ArrayList<EntityRoom>();

    public BusinessRow(List<EntityRoom> rooms) {
        this.rooms = rooms;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        for(EntityRoom room : this.rooms) {
            room.render(batch, camera);
        }
    }

    public void update(OrthographicCamera camera) {
        for(EntityRoom room : this.rooms) {
            room.update(camera);
        }
    }

}