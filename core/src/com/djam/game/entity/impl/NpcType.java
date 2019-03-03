package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;
import com.djam.game.entity.EntityNpc;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;
import com.djam.game.ui.text.Text;

public enum NpcType {
    Farmer("Farmer", "npc/farmer_0.png", "ui/farmer_overlay.png", 20),
    Researcher("Researcher", "npc/researcher_0.png", "ui/researcher_overlay.png", 35),
    Senior_Farmer("Experienced Farmer", 5, "npc/experiencedfarmer_0.png", "ui/experiencedfarmer_icon_overlay.png", "ui/experiencedfarmer_locked.png", 40),
    Experienced_Farmer_Farmer("Senior Farmer", 10, "npc/senior_farmer_0.png", "ui/senior_farmer_overlay.png", "ui/senior_farmer_locked.png", 80);

    ;

    NpcType(String name, String path, String overlayPath, int cost) {
        this.TEXTURE = Assets.getInstance().getTexture(path);

        this.SPRITE = new Sprite(this.TEXTURE);
        this.SPRITE.setAlpha(0.3f);

        this.OVERLAY_TEXTURE = Assets.getInstance().getTexture(overlayPath);
        this.OVERLAY_SPRITE = new Sprite(this.OVERLAY_TEXTURE);

        this.COST = cost;

        this.LOCKED = false;

        this.NAME = name;
    }

    NpcType(String name, int unlockCost, String path, String overlayPath, String lockedPath, int cost) {
        this.TEXTURE = Assets.getInstance().getTexture(path);

        this.SPRITE = new Sprite(this.TEXTURE);
        this.SPRITE.setAlpha(0.3f);

        this.OVERLAY_TEXTURE = Assets.getInstance().getTexture(overlayPath);
        this.OVERLAY_SPRITE = new Sprite(this.OVERLAY_TEXTURE);

        this.COST = cost;

        this.LOCKED = true;
        this.UNLOCK_COST = unlockCost;

        this.NAME = name;

        this.LOCKED_SPRITE = Assets.getInstance().getTexture(lockedPath);
    }

    public final Sprite SPRITE;
    public final Sprite OVERLAY_SPRITE;
    public final Texture OVERLAY_TEXTURE;
    public final Texture TEXTURE;

    public final int COST;

    public final boolean LOCKED;
    public int UNLOCK_COST;
    public Texture LOCKED_SPRITE;

    public final String NAME;

    public EntityNpc generateNpc(EntityRoom room, Map map, Vector2 position) {
        EntityNpc npc = null;

        switch(this) {
            case Farmer:
                npc = new EntityFarmer(room, map, position);
                break;
            case Researcher:
                npc = new EntityResearcher(room, map, position);
                break;
            case Senior_Farmer:
                npc = new EntitySeniorFarmer(room, map, position);
                break;
            case Experienced_Farmer_Farmer:
                npc = new EntityExperiencedFarmer(room, map, position);
                break;
        }

        System.out.println("Our type: " + this + " Returning " + npc);

        return npc;
    }

}
