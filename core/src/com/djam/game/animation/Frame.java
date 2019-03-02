package com.djam.game.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Frame {

    private Sprite sprite;

    public Frame(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(SpriteBatch batch, Vector2 position) {
        this.sprite.setPosition(position.x, position.y);
        this.sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
