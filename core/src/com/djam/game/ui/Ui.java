package com.djam.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class Ui {

    private Stage stage;

    private Table rootTable;

    private Skin skin;

    public Ui(Skin skin) {
        this.stage = new Stage();

        this.rootTable = new Table();
        this.rootTable.setFillParent(true);

        this.skin = skin;

        this.create();

        this.stage.addActor(this.rootTable);

        Gdx.input.setInputProcessor(this.stage);
    }

    public void render(SpriteBatch batch) {
        batch.end();
        this.stage.draw();
        batch.begin();
    }

    public void update(OrthographicCamera camera) {
        this.stage.act(Gdx.graphics.getDeltaTime());
    }

    public abstract void create();

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public Stage getStage() {
        return stage;
    }

    public Table getRootTable() {
        return rootTable;
    }

    public Skin getSkin() {
        return skin;
    }

}
