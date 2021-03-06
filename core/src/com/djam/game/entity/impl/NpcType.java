package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;
import com.djam.game.entity.EntityNpc;
import com.djam.game.entity.room.EntityRoom;
import com.djam.game.map.Map;
import com.djam.game.ui.impl.UnlockTypes;
import com.djam.game.ui.text.Text;

public enum NpcType {
    Farmer("Farmer", "npc/farmer_0.png", "ui/farmer_overlay.png", 20),
    Researcher("Researcher", "npc/researcher_0.png", "ui/researcher_overlay.png", 35),
    Senior_Farmer(null, null, UnlockTypes.EXPERIENCED_FARMER, "Experienced Farmer", 55, "npc/experiencedfarmer_0.png", "ui/experiencedfarmer_icon_overlay.png", "ui/experiencedfarmer_locked.png", 40),
    Experienced_Farmer_Farmer("Experienced Farmer", UnlockTypes.EXPERIENCED_FARMER, UnlockTypes.SENIOR_FARMER, "Senior Farmer", 130, "npc/senior_farmer_0.png", "ui/senior_farmer_overlay.png", "ui/senior_farmer_locked.png", 80),
    Head_Farmer("Senior Farmer", UnlockTypes.SENIOR_FARMER, UnlockTypes.HEAD_FARMER, "Head of Farming", 255, "npc/head_farmer_0.png", "ui/head_farmer_overlay.png", "ui/head_farmer_locked.png", 160)

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

    /**
     *
     * @param requiredType Required unlock to unlock this NPC
     * @param unlockType Next NPC unlock achieved by unlocking this
     */
    NpcType(String requirement, UnlockTypes requiredType, UnlockTypes unlockType, String name, int unlockCost, String path, String overlayPath, String lockedPath, int cost) {
        this.TEXTURE = Assets.getInstance().getTexture(path);

        this.SPRITE = new Sprite(this.TEXTURE);
        this.SPRITE.setAlpha(0.3f);

        this.OVERLAY_TEXTURE = Assets.getInstance().getTexture(overlayPath);
        this.OVERLAY_SPRITE = new Sprite(this.OVERLAY_TEXTURE);

        this.COST = cost;

        this.LOCKED = true;
        this.UNLOCK_COST = unlockCost * 3;

        this.NAME = name;

        this.LOCKED_SPRITE = Assets.getInstance().getTexture(lockedPath);

        this.UNLOCK_TYPE = unlockType;

        this.REQUIREMENT = requirement;

        this.REQUIRED_TYPE = requiredType;
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

    //The type which is unlocked when this NPC is purchased/unlocked
    public UnlockTypes UNLOCK_TYPE;

    public String REQUIREMENT;

    public UnlockTypes REQUIRED_TYPE;

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
            case Head_Farmer:
                npc = new EntityHeadFarmer(room, map, position);
                break;
        }

        System.out.println("Our type: " + this + " Returning " + npc);

        return npc;
    }

}
