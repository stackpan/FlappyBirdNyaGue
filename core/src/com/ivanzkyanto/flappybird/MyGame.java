package com.ivanzkyanto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ivanzkyanto.flappybird.states.GameStateManager;
import com.ivanzkyanto.flappybird.states.MenuState;

public class MyGame extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Flappy Bird";

	private SpriteBatch batch;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0.5f, 0.2f, 1f, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime() * 11/10);
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
