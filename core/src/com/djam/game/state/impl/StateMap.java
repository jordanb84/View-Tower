package com.djam.game.state.impl;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.entity.impl.EntityPlayer;
import com.djam.game.map.Map;
import com.djam.game.state.State;
import com.djam.game.state.StateManager;

public class StateMap extends State {

    private Map map;

    public StateMap(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void create() {
        this.map = new Map();

        EntityPlayer player = new EntityPlayer(this.map, new Vector2(100, 100));

        this.map.spawn(player);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.map.render(batch, camera);
    }

    @Override
    public void update(OrthographicCamera camera) {
        this.map.update(camera);
    }

}
