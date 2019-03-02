package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;
import com.djam.game.entity.EntityNpc;
import com.djam.game.map.Map;

public enum NpcType {
    Farmer("npc/farmer_0.png", "ui/farmer_overlay.png", 20);

    ;

    NpcType(String path, String overlayPath, int cost) {
        this.TEXTURE = Assets.getInstance().getTexture(path);

        this.SPRITE = new Sprite(this.TEXTURE);
        this.SPRITE.setAlpha(0.3f);

        this.OVERLAY_TEXTURE = Assets.getInstance().getTexture(overlayPath);
        this.OVERLAY_SPRITE = new Sprite(this.OVERLAY_TEXTURE);

        this.COST = cost;
    }

    public final Sprite SPRITE;
    public final Sprite OVERLAY_SPRITE;
    public final Texture OVERLAY_TEXTURE;
    public final Texture TEXTURE;

    public final int COST;

    public EntityNpc generateNpc(Map map, Vector2 position) {
        EntityNpc npc = null;

        switch(this) {
            case Farmer:
                npc = new EntityFarmer(map, position);
                break;
        }

        System.out.println("Our type: " + this + " Returning " + npc);

        return npc;
    }

}
