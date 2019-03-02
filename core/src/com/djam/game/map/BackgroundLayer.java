package com.djam.game.map;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BackgroundLayer {

    private float offsetMultiplier;

    private Sprite sprite;

    public BackgroundLayer(float offsetMultiplier, Sprite sprite) {
        this.offsetMultiplier = offsetMultiplier;
        this.sprite = sprite;

        if(offsetMultiplier == 1) {
            //sprite.setAlpha(0);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getOffsetMultiplier() {
        return offsetMultiplier;
    }

}
