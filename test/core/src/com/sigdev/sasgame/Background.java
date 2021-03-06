package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sigdev.sasgame.utils.Constants;

/**
 * Created by benedetto.sigillo on 20/04/2017.
 */

public class Background extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 25;

    public Background(SasGame game) {
        textureRegion = new TextureRegion(game.manager.get(Constants.BACKGROUND_IMAGE_PATH,Texture.class));
        textureRegionBounds1 = new Rectangle(0f ,(Gdx.graphics.getHeight()/ 2f), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        textureRegionBounds2 = new Rectangle(0f ,(0f- Gdx.graphics.getHeight()/ 2f), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void act(float delta) {
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y,Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.y - (delta * speed)) >= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.y -= delta * speed;
        textureRegionBounds2.y -= delta * speed;
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(0f, (0f-Gdx.graphics.getHeight()+4.6f), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed=speed;
    }

}
