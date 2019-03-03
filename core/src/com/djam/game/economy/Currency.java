package com.djam.game.economy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;
import com.djam.game.ui.text.TextType;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.text.DecimalFormat;

public class Currency {

    private float balance;

    private Sprite icon;

    private Vector2 position = new Vector2();

    private OrthographicCamera camera;

    public Currency() {
        this.balance = 350;

        this.icon = new Sprite(Assets.getInstance().getTexture("ui/coin.png"));
        this.icon.scale(3f);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        batch.setProjectionMatrix(this.camera.combined);

        this.position.set(30, 65);

        this.icon.setPosition(position.x, position.y);
        this.icon.draw(batch);

        String formattedValue = new DecimalFormat("#.##").format(this.balance); //"1.2";

        TextType.Default.FONT.draw(batch, "" + formattedValue + " coins", position.x + 25, position.y + 12);
    }

    public void update(OrthographicCamera camera) {

    }

    public void modifyBalance(float modification) {
        this.balance += modification;
    }

    public float getBalance() {
        return this.balance;
    }

}
