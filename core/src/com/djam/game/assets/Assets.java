package com.djam.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

        this.assetManager.load("skin/holo/Holo-dark-hdpi.json", Skin.class);

        this.loadTexture("ui/farmer_overlay.png");
        this.loadTexture("ui/coin.png");

        this.assetManager.load("skin/arcade/arcade-ui.json", Skin.class);

        this.loadTexture("ui/room_icon.png");
        this.loadTexture("ui/room_icon_hover.png");

        this.loadTexture("building/ladder.png");

        this.loadTexture("plant/flower_0.png");
        this.loadTexture("plant/flower_1.png");
        this.loadTexture("plant/flower_2.png");
        this.loadTexture("plant/flower_3.png");
        this.loadTexture("plant/flower_4.png");
        this.loadTexture("plant/flower_5.png");

        this.loadTexture("ui/coin_large.png");

        this.loadTexture("ui/happy_overlay_damage.png");
        this.loadTexture("ui/happy_overlay_damage_0.png");
        this.loadTexture("ui/happy_overlay_damage_1.png");
        this.loadTexture("ui/happy_overlay_damage_2.png");
        this.loadTexture("ui/happy_overlay_full.png");
        this.loadTexture("ui/heart_full.png");

        this.loadTexture("background/1.png");
        this.loadTexture("background/2.png");
        this.loadTexture("background/3.png");
        this.loadTexture("background/4.png");
        this.loadTexture("background/5.png");
        this.loadTexture("background/6.png");
        this.loadTexture("background/7.png");

        this.loadTexture("ui/painting_icon.png");
        this.loadTexture("ui/painting_icon_hover.png");

        this.loadTexture("decor/painting_0.png");
        this.loadTexture("decor/painting_1.png");
        this.loadTexture("decor/painting_2.png");
        this.loadTexture("decor/painting_3.png");
        this.loadTexture("decor/painting_4.png");
        this.loadTexture("decor/painting_5.png");
        this.loadTexture("decor/painting_6.png");
        this.loadTexture("decor/painting_7.png");

        this.loadTexture("ui/research.png");
        this.loadTexture("ui/research_large.png");

        this.loadTexture("npc/researcher_0.png");
        this.loadTexture("npc/researcher_1.png");
        this.loadTexture("npc/researcher_2.png");
        this.loadTexture("npc/researcher_3.png");
        this.loadTexture("npc/researcher_4.png");
        this.loadTexture("npc/researcher_5.png");

        this.loadTexture("ui/researcher_overlay.png");

        this.loadTexture("bookshelf/bookshelf_0.png");
        this.loadTexture("bookshelf/bookshelf_1.png");
        this.loadTexture("bookshelf/bookshelf_2.png");
        this.loadTexture("bookshelf/bookshelf_3.png");

        this.loadTexture("npc/senior_farmer_0.png");
        this.loadTexture("npc/senior_farmer_1.png");
        this.loadTexture("npc/senior_farmer_2.png");
        this.loadTexture("npc/senior_farmer_3.png");
        this.loadTexture("npc/senior_farmer_4.png");
        this.loadTexture("npc/senior_farmer_5.png");

        this.loadTexture("npc/senior_researcher_0.png");
        this.loadTexture("npc/senior_researcher_1.png");
        this.loadTexture("npc/senior_researcher_2.png");
        this.loadTexture("npc/senior_researcher_3.png");
        this.loadTexture("npc/senior_researcher_4.png");
        this.loadTexture("npc/senior_researcher_5.png");

        this.loadTexture("npc/head_farmer_0.png");
        this.loadTexture("npc/head_farmer_1.png");
        this.loadTexture("npc/head_farmer_2.png");
        this.loadTexture("npc/head_farmer_3.png");
        this.loadTexture("npc/head_farmer_4.png");
        this.loadTexture("npc/head_farmer_5.png");

        this.loadTexture("ui/senior_farmer_overlay.png");
        this.loadTexture("ui/senior_farmer_locked.png");

        this.loadTexture("ui/heart_icon.png");

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

    public Skin getSkin(String path) {
        return this.assetManager.get(path, Skin.class);
    }

}
