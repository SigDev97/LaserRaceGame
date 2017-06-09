package com.sigdev.sasgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SasGame extends Game {

	public AssetManager manager = new AssetManager();

	@Override
	public void create () {

		setScreen(new LoadingScreen(this));
		//setScreen(new MenuScreen());
		//setScreen(new GameScreen());

	}
}
