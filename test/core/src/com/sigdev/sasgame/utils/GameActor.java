package com.sigdev.sasgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public abstract class GameActor extends Actor {

    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
    }

    public abstract UserData getUserData();

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            // This means the world destroyed the body (enemy or runner went out of bounds)
            remove();
        }

    }

    private void updateRectangle() {
        screenRectangle.x = transformToScreenX(body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = transformToScreenY(body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = transformToScreenX(userData.getWidth());
        screenRectangle.height = transformToScreenY(userData.getHeight());
    }

    protected float transformToScreen(float n) {

        return Constants.WORLD_TO_SCREEN * n;
    }

    protected float transformToScreenX(float n) {

        return (Gdx.graphics.getWidth()/15)*n;
    }

    protected float transformToScreenY(float n) {

        return (Gdx.graphics.getHeight()/25)*n;
    }
}
