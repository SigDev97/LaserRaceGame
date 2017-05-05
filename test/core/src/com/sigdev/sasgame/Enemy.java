package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.GameActor;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class Enemy extends GameActor {

    private final TextureRegion textureRegion1,textureRegion2;
    private Rectangle textureRegionBounds1;
    private Sprite back;

    private float changeAlpha;
    private float alpha;


    public Enemy(Body body) {
        super(body);
        textureRegion1 = new TextureRegion(new Texture(Gdx.files.internal(Constants.ENEMY_SQUARE_BACK)));
        textureRegion2 = new TextureRegion(new Texture(Gdx.files.internal(Constants.ENEMY_SQUARE_OVER)));

        back=new Sprite(textureRegion1);
        back.setBounds(screenRectangle.x, screenRectangle.y+10, screenRectangle.getWidth(),
                screenRectangle.getHeight());

        back.setAlpha(1f);
        back.setColor(Color.ORANGE);

        alpha=1;
        changeAlpha=0;
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        batch.draw(textureRegion2, screenRectangle.x, screenRectangle.y+10, screenRectangle.getWidth(),
                screenRectangle.getHeight());

        back.setBounds(screenRectangle.x, screenRectangle.y+10, screenRectangle.getWidth(),
                screenRectangle.getHeight());
        back.draw(batch);

        changeAlpha+=(100*Gdx.graphics.getDeltaTime());
        if(changeAlpha>4)
        {
            changeAlpha=0;
            if(alpha==1)
            {
                alpha=0.75f;
            }
            else
            {
                alpha=1;
            }
        }

        back.setAlpha(alpha);


    }

}
