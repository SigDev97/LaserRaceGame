package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by benedetto.sigillo on 13/04/2017.
 */

public class GameScreen implements Screen {

    private GameStage gameStage;

    public GameScreen()
    {
        gameStage=new GameStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.draw();
        gameStage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
        Gdx.app.log("UpdateViewPort","W-"+width+" H-"+height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
