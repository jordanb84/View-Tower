package com.djam.game.entity.impl;

public enum NpcItemType {
    Flowers(1, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png"),
    Bookshelf(1, "bookshelf/bookshelf_0.png", "bookshelf/bookshelf_1.png", "bookshelf/bookshelf_2.png", "bookshelf/bookshelf_3.png"),
    SuperFlowers(3, "plant/flower1_0.png", "plant/flower1_1.png", "plant/flower1_2.png", "plant/flower1_3.png", "plant/flower1_4.png", "plant/flower1_5.png"),
    SeniorFlowers(6, "plant/pot_0.png", "plant/pot_1.png", "plant/pot_2.png", "plant/pot_3.png"),
    HeadFlowers(12, "plant/pot1_0.png", "plant/pot1_1.png", "plant/pot1_2.png", "plant/pot1_3.png"),
    ;

    NpcItemType(int profit, String ... stageSpritePaths) {
        this.PROFIT = profit * 3;
        this.STAGE_SPRITE_PATHS = stageSpritePaths;
    }

    public final int PROFIT;
    public final String[] STAGE_SPRITE_PATHS;

}
