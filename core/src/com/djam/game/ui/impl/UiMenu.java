package com.djam.game.ui.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.djam.game.map.Background;
import com.djam.game.state.StateManager;
import com.djam.game.ui.SkinType;
import com.djam.game.ui.Ui;
import com.sun.org.apache.xpath.internal.operations.Or;

public class UiMenu extends Ui {

    private Background background;

    private OrthographicCamera camera;

    private Vector2 mousePosition = new Vector2();

    private MouseListener mouseListener;

    public UiMenu(StateManager stateManager) {
        super(stateManager, SkinType.Holo_Dark_Hdpi.SKIN);
    }

    @Override
    public void create() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        this.camera.position.add(0, 0, 0);
        this.camera.update();

        this.background = new Background();

        ButtonPlay play = new ButtonPlay(this.getSkin(), this.getStateManager());
        play.setPosition(Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, Gdx.graphics.getHeight() / 2 - play.getHeight() / 2 + play.getHeight());


        ButtonExit exit = new ButtonExit(this.getSkin(), this.getStateManager());
        exit.setPosition(Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, Gdx.graphics.getHeight() / 2 - exit.getHeight() / 2 - exit.getHeight() + exit.getHeight());

        exit.setSize(play.getWidth(), play.getHeight());

        ButtonCredits credits = new ButtonCredits(this.getSkin(), this.getStateManager());
        credits.setPosition(Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, Gdx.graphics.getHeight() / 2 - exit.getHeight() / 2 - exit.getHeight());

        credits.setSize(play.getWidth(), play.getHeight());

        this.getRootTable().center().addActor(play);
        this.getRootTable().center().addActor(exit);
        this.getRootTable().center().addActor(credits);

        this.getRootTable().setFillParent(true);

        this.mouseListener = new MouseListener();
        this.getStage().addListener(this.mouseListener);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.end();
        batch.setProjectionMatrix(this.camera.combined);
        batch.begin();
        this.background.render(batch, this.camera);

        super.render(batch);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        this.mouseListener.update(this.camera);
    }

}

class MouseListener extends InputListener {

    private Vector2 mouse = new Vector2();

    private Vector2 lastMouse = new Vector2();

    private Vector2 diff = new Vector2();

    public MouseListener() {

    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        //System.out.println("Mouse moved by " + x + "/" + y);
        return super.mouseMoved(event, x, y);
    }

    public void update(OrthographicCamera camera) {
        this.diff.set(0, 0);

        Vector3 mouse3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

       // camera.unproject(mouse3);

        this.mouse.set(mouse3.x, mouse3.y);

        //this.diff.set(this.lastMouse.x - this.mouse.x, this.diff.y);

        this.diff.set(this.lastMouse.x - this.mouse.x, this.lastMouse.y - this.mouse.y);

        //System.out.println("Last mouse X: " + this.lastMouse.x + " Current mouse X " + this.mouse.x + " Diff " + this.diff.x);

        diff.set(diff.x * 2f, diff.y * 1.6f);

        if(diff.x < 100 && diff.y < 100) {
            //System.out.println("Diff " + this.diff.x + "/" + this.diff.y);

            //camera.position.add(0, diff.y, 0);
            //camera.update();
        }

        //System.out.println(camera.position.x + "/" + camera.position.y);

        if(camera.position.y < 7) {
            camera.position.y = 7;
            camera.update();
        }

        this.lastMouse.set(this.mouse.x, this.mouse.y);

    }

}

class ButtonPlay extends TextButton {

    public ButtonPlay(Skin skin, StateManager stateManager) {
        super("Play Game", skin);
        this.addListener(new PlayClicked(stateManager));
    }

    public void play() {

    }

    class PlayClicked extends ClickListener {

        private StateManager stateManager;

        public PlayClicked(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            stateManager.setActiveState("map");
            super.clicked(event, x, y);
        }
    }
}

class ButtonExit extends TextButton {

    public ButtonExit(Skin skin, StateManager stateManager) {
        super("Exit Game", skin);
        this.addListener(new ExitClicked(stateManager));
    }

    public void play() {

    }

    class ExitClicked extends ClickListener {

        private StateManager stateManager;

        public ExitClicked(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.exit(0);
            super.clicked(event, x, y);
        }
    }
}

class ButtonCredits extends TextButton {

    public ButtonCredits(Skin skin, StateManager stateManager) {
        super("Credits", skin);
        TextTooltip tooltip = new TextTooltip("Programmed from scratch in 48 hours for Discord Jam 1.\n\nAssets from OpenGameArt users:\n\"OddPotatoGift\"\n\"CraftPix.net 2D Game Assets\"", SkinType.Arcade.SKIN);
        tooltip.setInstant(true);
        this.addListener(tooltip);
    }

}