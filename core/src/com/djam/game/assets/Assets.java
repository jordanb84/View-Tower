package com.djam.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.djam.game.animation.Animation;

public class Assets {

    private static final Assets instance = new Assets();

    private AssetManager assetManager = new AssetManager();

    public Assets() {
        this.loadAssets();
    }

    private void loadAssets() {
        this.loadTexture("player/player_idle_0.png");
        this.loadTexture("player/player_idle_1.png");
        this.loadTexture("player/player_idle_2.png");
        this.loadTexture("player/player_idle_3.png");
        this.loadTexture("player/player_idle_4.png");
        this.loadTexture("player/player_idle_5.png");
        this.loadTexture("player/player_idle_6.png");
        this.loadTexture("player/player_idle_7.png");
        this.loadTexture("player/player_idle_8.png");
        this.loadTexture("player/player_idle_9.png");
        this.loadTexture("player/player_idle_10.png");
        this.loadTexture("player/player_idle_11.png");

        this.loadTexture("player/player_run_0.png");
        this.loadTexture("player/player_run_1.png");
        this.loadTexture("player/player_run_2.png");
        this.loadTexture("player/player_run_3.png");
        this.loadTexture("player/player_run_4.png");
        this.loadTexture("player/player_run_5.png");
        this.loadTexture("player/player_run_6.png");

        this.loadTexture("player/player_run_0_left.png");
        this.loadTexture("player/player_run_1_left.png");
        this.loadTexture("player/player_run_2_left.png");
        this.loadTexture("player/player_run_3_left.png");
        this.loadTexture("player/player_run_4_left.png");
        this.loadTexture("player/player_run_5_left.png");
        this.loadTexture("player/player_run_6_left.png");

        this.loadTexture("player/player_idle_0_left.png");
        this.loadTexture("player/player_idle_1_left.png");
        this.loadTexture("player/player_idle_2_left.png");
        this.loadTexture("player/player_idle_3_left.png");
        this.loadTexture("player/player_idle_4_left.png");
        this.loadTexture("player/player_idle_5_left.png");
        this.loadTexture("player/player_idle_6_left.png");
        this.loadTexture("player/player_idle_7_left.png");
        this.loadTexture("player/player_idle_8_left.png");
        this.loadTexture("player/player_idle_9_left.png");
        this.loadTexture("player/player_idle_10_left.png");
        this.loadTexture("player/player_idle_11_left.png");

        this.loadTexture("building/room.png");
        this.loadTexture("building/room_blank.png");
        this.loadTexture("building/room_invisible.png");
        this.loadTexture("building/room_collision.png");
        this.loadTexture("building/desk.png");

        this.loadTexture("npc/farmer_0.png");
        this.loadTexture("npc/farmer_1.png");
        this.loadTexture("npc/farmer_2.png");
        this.loadTexture("npc/farmer_3.png");
        this.loadTexture("npc/farmer_4.png");
        this.loadTexture("npc/farmer_5.png");

        this.assetManager.finishLoading();
    }

    public static Assets getInstance() {
        return instance;
    }

    public Texture getTexture(String path) {
        return this.assetManager.get(path, Texture.class);
    }

    public void loadTexture(String path) {
        this.assetManager.load(path, Texture.class);
    }

}
