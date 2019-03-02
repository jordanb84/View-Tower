package com.djam.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.djam.game.assets.Assets;

public enum SkinType {
    Holo_Dark_Hdpi("skin/holo/Holo-dark-hdpi.json"), Arcade("skin/arcade/arcade-ui.json")
    ;

    SkinType(String path) {
        this.SKIN = Assets.getInstance().getSkin(path);
    }

    public final Skin SKIN;

}
