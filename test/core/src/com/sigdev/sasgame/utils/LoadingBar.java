package com.sigdev.sasgame.utils;

/**
 * Created by Utente on 09/06/2017.
 */

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBar extends Actor {

    Animation animation;
    TextureRegion reg;
    float stateTime;

    public LoadingBar(Animation animation) {
        this.animation = animation;
        reg = (TextureRegion) animation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        reg = (TextureRegion) animation.getKeyFrame(stateTime);
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(reg, getX(), getY());
    }
}