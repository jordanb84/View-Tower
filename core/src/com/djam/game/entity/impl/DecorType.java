package com.djam.game.entity.impl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.djam.game.assets.Assets;

import java.util.ArrayList;
import java.util.List;

public enum  DecorType {
    Painting(null, 30, 0.1f, false, "Painting", "Improves employee view in a room! Place in any room", 25, 0.3f, "decor/painting_0.png", "decor/painting_1.png", "decor/painting_2.png", "decor/painting_3.png", "decor/painting_4.png", "decor/painting_5.png", "decor/painting_6.png", "decor/painting_7.png"),
    Fireflies(Color.GREEN, 40, 0.1f, true, "Jar of Fireflies", "Improves employee view in a room! Place in any room", 25, 0.3f, "decor/firefly_0.png", "decor/firefly_1.png", "decor/firefly_2.png", "decor/firefly_3.png"),
    Tv(Color.YELLOW, 60, 0.4f, true, "Television", "Improves employee view in a room! Place in any room", 25, 0.3f, "decor/tv_0.png", "decor/tv_1.png"),

    ;

    DecorType(boolean animated, String name, String description, int price, float happinessBonus, String spritePath) {
        this.SPRITES = new ArrayList<String>();
        this.SPRITES.add(spritePath);

        this.HAPPINESS_BONUS = happinessBonus * 4;

        this.NAME = name;
        this.DESCRIPTION = description + "\n(Better view helps employees make more money)";

        this.PRICE = price;

        this.ANIMATED = animated;

        this.INTERVAL = 0.2f;

        this.LIGHT_DISTANCE = 30;
    }

    DecorType(Color lightColor, float lightDistance, float interval, boolean animated, String name, String description, int price, float happinessBonus, String ... spritePaths) {
        this.SPRITES = new ArrayList<String>();

        for(String spritePath : spritePaths) {
            this.SPRITES.add(spritePath);
        }

        this.HAPPINESS_BONUS = happinessBonus * 1.5f;

        this.NAME = name;
        this.DESCRIPTION = description + "\n(Better view helps employees make more money)";

        this.PRICE = price;

        this.ANIMATED = animated;

        this.LIGHT_COLOR = lightColor;

        this.INTERVAL = interval;

        this.LIGHT_DISTANCE = lightDistance;
    }

    public final List<String> SPRITES;
    public final float HAPPINESS_BONUS;
    public final String NAME;
    public final String DESCRIPTION;
    public final int PRICE;
    public final boolean ANIMATED;
    public Color LIGHT_COLOR;
    public final float INTERVAL;
    public final float LIGHT_DISTANCE;

}
