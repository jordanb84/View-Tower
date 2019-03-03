package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.djam.game.assets.Assets;

import java.util.ArrayList;
import java.util.List;

public enum  DecorType {
    Painting("Painting", "Improves employee view in a room! Place in any room", 25, 0.3f, "decor/painting_0.png", "decor/painting_1.png", "decor/painting_2.png", "decor/painting_3.png", "decor/painting_4.png", "decor/painting_5.png", "decor/painting_6.png", "decor/painting_7.png")

    ;

    DecorType(String name, String description, int price, float happinessBonus, String spritePath) {
        this.SPRITES = new ArrayList<String>();
        this.SPRITES.add(spritePath);

        this.HAPPINESS_BONUS = happinessBonus;

        this.NAME = name;
        this.DESCRIPTION = description + "\n(Better view helps employees make more money)";

        this.PRICE = price;
    }

    DecorType(String name, String description, int price, float happinessBonus, String ... spritePaths) {
        this.SPRITES = new ArrayList<String>();

        for(String spritePath : spritePaths) {
            this.SPRITES.add(spritePath);
        }

        this.HAPPINESS_BONUS = happinessBonus;

        this.NAME = name;
        this.DESCRIPTION = description + "\n(Better view helps employees make more money)";

        this.PRICE = price;
    }

    public final List<String> SPRITES;
    public final float HAPPINESS_BONUS;
    public final String NAME;
    public final String DESCRIPTION;
    public final int PRICE;

}
