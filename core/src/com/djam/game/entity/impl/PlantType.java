package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.g2d.Sprite;

public enum  PlantType {
    Flowers(1, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png")

    ;

    PlantType(int profit, String ... stageSpritePaths) {
        this.PROFIT = profit;
        this.STAGE_SPRITE_PATHS = stageSpritePaths;
    }

    public final int PROFIT;
    public final String[] STAGE_SPRITE_PATHS;

}
