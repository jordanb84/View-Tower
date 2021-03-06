package com.djam.game.ui.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.logging.FileHandler;

public enum  TextType {
    Default("ui/font.ttf", 12), Default_Small("ui/font.ttf", 4), Default_Medium("ui/font.ttf", 6)
    ;

    TextType(String path, int size) {
        FileHandle file = Gdx.files.internal(path);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(file);

        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;

        this.FONT = fontGenerator.generateFont(fontParameter);
    }

    public final BitmapFont FONT;

    /**
     *     FileHandle fontFile = Gdx.files.internal("data/Roboto-Bold.ttf");
     *     FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
     *     FreeTypeFontParameter parameter = new FreeTypeFontParameter();
     *     parameter.size = 12;
     *     textFont = generator.generateFont(parameter);
     *     parameter.size = 24;
     *     titleFont = generator.generateFont(parameter);
     *     generator.dispose();
     */
}
