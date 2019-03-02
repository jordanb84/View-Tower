package com.djam.game.happiness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;
import com.djam.game.map.Map;
import com.djam.game.ui.text.TextType;

public class Happiness {

    private Sprite currentHappinessOverlay;

    private Sprite happinessOverlayFull;
    private Sprite happinessOverlayFullDamage;

    private Sprite happinessOverlayMinorDamage;
    private Sprite happinessOverlayModerateDamage;
    private Sprite happinessOverlayHeavyDamage;

    private Sprite heartFull;

    private Sprite currentHeart;

    private Map map;

    private Vector2 position = new Vector2();

    private float scale;

    private float percentage = 80;

    public Happiness(Map map) {
        Assets assets = Assets.getInstance();

        this.happinessOverlayFull = new Sprite(assets.getTexture("ui/happy_overlay_full.png"));

        this.happinessOverlayMinorDamage = new Sprite(assets.getTexture("ui/happy_overlay_damage_0.png"));
        this.happinessOverlayModerateDamage = new Sprite(assets.getTexture("ui/happy_overlay_damage_1.png"));
        this.happinessOverlayHeavyDamage = new Sprite(assets.getTexture("ui/happy_overlay_damage_2.png"));

        this.happinessOverlayFullDamage = new Sprite(assets.getTexture("ui/happy_overlay_damage.png"));

        this.heartFull = new Sprite(assets.getTexture("ui/heart_full.png"));

        this.currentHappinessOverlay = this.happinessOverlayFull;
        this.currentHeart = this.heartFull;

        this.map = map;

        this.scale = 4;

        this.happinessOverlayFull.setScale(this.scale);
        this.happinessOverlayMinorDamage.setScale(this.scale);
        this.happinessOverlayModerateDamage.setScale(this.scale);
        this.happinessOverlayHeavyDamage.setScale(this.scale);
        this.happinessOverlayFullDamage.setScale(this.scale);
        this.heartFull.setScale(this.scale);

        this.position.add(Gdx.graphics.getWidth() / 2 /**- (this.happinessOverlayFull.getWidth() * scale / 2)**/, this.happinessOverlayFull.getHeight() * this.scale);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        batch.setProjectionMatrix(this.map.getTextCamera().combined);
        this.happinessOverlayFullDamage.setPosition(this.position.x, this.position.y);
        this.happinessOverlayFullDamage.draw(batch);

        float diff = - 64 * (this.percentage / 100) + 64;

        //System.out.println("Diff " + diff);

        this.happinessOverlayFull.setPosition(this.position.x + diff, this.position.y);
        //this.happinessOverlayFull.setScale(this.scale * (this.percentage / 100), this.scale);
        this.happinessOverlayFull.setSize(64 * (this.percentage / 100), this.happinessOverlayFull.getHeight());
        this.happinessOverlayFull.draw(batch);

        this.currentHeart.setPosition(this.position.x, this.position.y);
        this.currentHeart.draw(batch);

        TextType.Default.FONT.draw(batch, "Employee Happiness", this.position.x - (12 * this.scale) + 12, this.position.y);
    }

    public void update(OrthographicCamera camera) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            this.percentage += 10;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            this.percentage -= 10;
        }
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

}
