package com.djam.game.state.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.djam.game.state.State;
import com.djam.game.state.StateManager;
import com.djam.game.ui.impl.UiMenu;

public class StateMenu extends State {

    private UiMenu menu;

    public StateMenu(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void create() {
        this.menu = new UiMenu(this.getManager());
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.menu.render(batch);
    }

    @Override
    public void update(OrthographicCamera camera) {
        this.menu.update(camera);
    }

    @Override
    public void resize(int width, int height) {
        this.menu.resize(width, height);
    }

}
