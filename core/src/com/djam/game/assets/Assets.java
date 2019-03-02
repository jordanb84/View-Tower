package com.djam.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

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
