package com.djam.game.state;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class StateManager {

    private HashMap<String, State> states = new HashMap<String, State>();

    private State activeState;

    public StateManager() {

    }

    public void renderActiveState(SpriteBatch batch, OrthographicCamera camera) {
        this.getActiveState().render(batch, camera);
    }

    public void updateActiveState(OrthographicCamera camera) {
        this.getActiveState().update(camera);
    }

    public void resize (int width, int height) {

    }

        public void setActiveState(String name) {
        this.activeState = this.getState(name);
    }

    public State getActiveState() {
        return this.activeState;
    }

    public void registerState(String name, State state) {
        this.states.put(name, state);
    }

    public State getState(String name) {
        return this.states.get(name);
    }
}