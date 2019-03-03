package com.djam.game.entity.impl;

public enum NpcItemType {
    Flowers(1, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png"),
    Bookshelf(1, "bookshelf/bookshelf_0.png", "bookshelf/bookshelf_1.png", "bookshelf/bookshelf_2.png", "bookshelf/bookshelf_3.png"),
    SuperFlowers(3, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png"),
    SeniorFlowers(9, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png"),
    HeadFlowers(17, "plant/flower_0.png", "plant/flower_1.png", "plant/flower_2.png", "plant/flower_3.png", "plant/flower_4.png", "plant/flower_5.png"),
    ;

    NpcItemType(int profit, String ... stageSpritePaths) {
        this.PROFIT = profit;
        this.STAGE_SPRITE_PATHS = stageSpritePaths;
    }

    public final int PROFIT;
    public final String[] STAGE_SPRITE_PATHS;

}
