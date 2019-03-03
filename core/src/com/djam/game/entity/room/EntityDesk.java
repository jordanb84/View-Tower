package com.djam.game.entity.room;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityNpc;
import com.djam.game.entity.EntityNpcItem;
import com.djam.game.entity.impl.EntityBookshelf;
import com.djam.game.entity.impl.EntityPlant;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.entity.impl.NpcItemType;
import com.djam.game.map.Map;

public class EntityDesk extends Entity {

    private EntityNpc npc;

    private boolean placing;

    private NpcType npcType;

    private EntityNpcItem npcItem;

    private EntityRoom room;

    public EntityDesk(EntityRoom room, Map map, Vector2 position) {
        super(map, position);

        //this.npc = new EntityFarmer(map, new Vector2(position.x, position.y));
        //this.npc.getPosition().add(-this.npc.getWidth() / 2, 0);

        this.room = room;
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("building/desk.png");

        return animation;
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        if(this.hasNpc()) {
            this.npc.render(batch, camera);

            if(this.hasItem()) {
                this.npcItem.render(batch, camera);
            }
        }

        if(this.placing) {
            this.npcType.SPRITE.setPosition(this.getPosition().x, this.getPosition().y);
            this.npcType.SPRITE.draw(batch);

            //System.out.println("Placing " + this.placing + " has npc " + this.hasNpc());
        }

        super.render(batch, camera);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        if(this.hasNpc()) {
            this.npc.update(camera);

            if(this.hasItem()) {
                this.npcItem.update(camera);
            }
        }

    }

    public boolean hasNpc() {
        return this.npc != null;
    }

    public void placing(NpcType npcType) {
        this.placing = true;
        this.npcType = npcType;
    }

    public void unplace() {
        this.placing = false;
    }

    public void placeNpc(NpcType npcType) {
        EntityNpc placedNpc = npcType.generateNpc(this.room, this.getMap(), new Vector2(this.getPosition()));

        placedNpc.getPosition().add((-this.getWidth() - this.getWidth() / 2), 0);

        this.npc = placedNpc;

        switch(npcType) {
            case Farmer:
                this.placeItem(NpcItemType.Flowers);
                break;
            case Researcher:
                this.placeItem(NpcItemType.Bookshelf);
                break;
            case Senior_Farmer:
                this.placeItem(NpcItemType.SuperFlowers);
                break;
            case Experienced_Farmer_Farmer:
                this.placeItem(NpcItemType.SeniorFlowers);
                break;
        }

    }

    private void placeItem(NpcItemType itemType) {
        switch(itemType) {
            case Flowers:
                this.npcItem = new EntityPlant(itemType, this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y));
                break;
            case Bookshelf:
                this.npcItem = new EntityBookshelf(itemType, this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y));
                break;
            case SuperFlowers:
                this.npcItem = new EntityPlant(itemType, this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y));
                break;
            case SeniorFlowers:
                this.npcItem = new EntityPlant(itemType, this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y));
                break;
        }

        this.npcItem.getPosition().add(0, this.getHeight());
    }

    public boolean hasItem() {
        return this.npcItem != null;
    }

    public EntityNpc getNpc() {
        return npc;
    }

}
