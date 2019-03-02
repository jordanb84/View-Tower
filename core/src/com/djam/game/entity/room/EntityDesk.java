package com.djam.game.entity.room;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityNpc;
import com.djam.game.entity.impl.EntityFarmer;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.map.Map;

public class EntityDesk extends Entity {

    private EntityNpc npc;

    private boolean placing;

    private NpcType npcType;

    public EntityDesk(Map map, Vector2 position) {
        super(map, position);

        //this.npc = new EntityFarmer(map, new Vector2(position.x, position.y));
        //this.npc.getPosition().add(-this.npc.getWidth() / 2, 0);
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
        }

        if(this.placing) {
            this.npcType.SPRITE.setPosition(this.getPosition().x, this.getPosition().y);
            this.npcType.SPRITE.draw(batch);
        }

        super.render(batch, camera);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        if(this.hasNpc()) {
            this.npc.update(camera);
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
        EntityNpc placedNpc = null;

        switch(npcType) {
            case Farmer:
                placedNpc = new EntityFarmer(this.getMap(), new Vector2(this.getPosition().x, this.getPosition().y));
                break;
        }

        this.npc = placedNpc;
    }

}
