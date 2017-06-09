package com.sigdev.sasgame;

import com.badlogic.gdx.Screen;

/**
 * Created by Utente on 09/06/2017.
 */

public abstract class AbstractScreen implements Screen {

    protected SasGame game;

    public AbstractScreen(SasGame game) {
        this.game = game;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
