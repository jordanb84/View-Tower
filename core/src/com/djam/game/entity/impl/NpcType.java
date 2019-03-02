package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.djam.game.assets.Assets;

public enum NpcType {
    Farmer("npc/farmer_0.png");

    ;

    NpcType(String path) {
        this.SPRITE = new Sprite(Assets.getInstance().getTexture(path));
        this.SPRITE.setAlpha(0.3f);
    }

    public final Sprite SPRITE;

}
