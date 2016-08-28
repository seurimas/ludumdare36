package com.youllknow.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.youllknow.game.input.InputMarshal;

public class LudumDare36Game extends Game {
	public SpriteBatch batch, uiBatch;
	public ShapeRenderer shapes, uiShapes;
	public final AssetManager assets = new AssetManager();
	Texture img;
	public InputMarshal input;
	public static final String MAIN_TEXTURE = "Main.png";
	public static final String ENTRANCE_TUNE = "entrance_tune.mp3";
	public static final String MAIN_THEME = "MainTheme.mp3";
	private static final String[] textures = {
			MAIN_TEXTURE,
	};
	private static final String[] music = {
			ENTRANCE_TUNE,
			MAIN_THEME
	};
	@Override
	public void create () {
		batch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		shapes = new ShapeRenderer();
		uiShapes = new ShapeRenderer();
		input = new InputMarshal();
		for (String texture : textures) {
			assets.load(texture, Texture.class);
		}
		for (String song : music) {
			assets.load(song, Music.class);
		}
	}
	@Override
	public void render() {
		if (!assets.update()) {
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			float progress = assets.getProgress();
			shapes.begin(ShapeType.Filled);
			float left = Gdx.graphics.getWidth() / 4;
			float fullWidth = Gdx.graphics.getWidth() / 2;
			float width = progress * fullWidth;
			shapes.rect(left, Gdx.graphics.getHeight() / 2 - 25, width, 50);
			shapes.end();
			shapes.begin(ShapeType.Line);
			shapes.rect(left, Gdx.graphics.getHeight() / 2 - 25, fullWidth, 50);
			shapes.end();
		} else {
			if (getScreen() == null)
				setScreen(new MainGameScreen(this));
			super.render();
		}
	}
}
