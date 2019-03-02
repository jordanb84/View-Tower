package com.djam.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.djam.game.state.StateManager;
import com.djam.game.state.impl.StateMap;

public class Game extends ApplicationAdapter {

	private SpriteBatch batch;

	private OrthographicCamera camera;

	private StateManager stateManager;

	@Override
	public void create () {
		this.batch = new SpriteBatch();

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.stateManager = new StateManager();

		this.stateManager.registerState("map", new StateMap(this.stateManager));
		this.stateManager.setActiveState("map");
	}

	@Override
	public void render () {
		this.stateManager.updateActiveState(this.camera);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		this.stateManager.renderActiveState(this.batch, this.camera);
		this.batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
