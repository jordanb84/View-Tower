package com.djam.game.state.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.entity.impl.EntityPlayer;
import com.djam.game.map.Map;
import com.djam.game.state.State;
import com.djam.game.state.StateManager;
import com.djam.game.ui.impl.UiHud;

public class StateMap extends State {

    private Map map;

    private UiHud uiHud;

    public StateMap(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void create() {
        this.map = new Map();

        EntityPlayer player = new EntityPlayer(this.map, new Vector2(100, 30));

        this.map.spawn(player);

        this.uiHud = new UiHud(this.map.getBusiness(), this.map.getCurrency());
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.map.render(batch, camera);

        this.uiHud.render(batch);
    }

    @Override
    public void update(OrthographicCamera camera) {
        this.map.update(camera);

        this.uiHud.update(camera);
    }

    @Override
    public void resize(int width, int height) {
        this.uiHud.resize(width, height);
    }

}
