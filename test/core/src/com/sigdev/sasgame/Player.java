package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.GameActor;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class Player extends GameActor {

    public int going=0;
    private boolean hit=false;

    public ShaderProgram shaderOutline;

    private final TextureRegion textureRegion;

    public Player(Body body) {
        super(body);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.PLAYER_BASE_PATH)));
    }

    @Override
    public PlayerUserData getUserData() {
        return (PlayerUserData) userData;
    }

    public void left()
    {
        if(going==0)
        {
            going=1;
            body.applyLinearImpulse(getUserData().getLeftLinearImpulse(), body.getWorldCenter(), true);


        }
        else if (going==2)
        {
            going=1;
            body.applyLinearImpulse(getUserData().getLeftLinearImpulse(), body.getWorldCenter(), true);
            body.applyLinearImpulse(getUserData().getLeftLinearImpulse(), body.getWorldCenter(), true);
        }

    }

    public void right()
    {
        if(going==0)
        {
            going=2;
            body.applyLinearImpulse(getUserData().getRightLinearImpulse(), body.getWorldCenter(), true);

        }
        else if(going==1)
        {
            going=2;
            body.applyLinearImpulse(getUserData().getRightLinearImpulse(), body.getWorldCenter(), true);
            body.applyLinearImpulse(getUserData().getRightLinearImpulse(), body.getWorldCenter(), true);
        }
    }

    public void hit() {
        if(body.getPosition().x>7)
        {
            body.applyLinearImpulse(getUserData().getLULinearImpulse(), body.getWorldCenter(), true);
        }
        else
        {
            body.applyLinearImpulse(getUserData().getRULinearImpulse(), body.getWorldCenter(), true);
        }
        body.applyAngularImpulse(getUserData().getHitImpulse(), true);
        hit = true;
    }

    public void hitLeft() {
        body.applyLinearImpulse(getUserData().getRULinearImpulse(), body.getWorldCenter(), true);
        body.applyAngularImpulse(getUserData().getHitImpulse(), true);

        hit = true;
    }

    public void hitRight() {
        body.applyLinearImpulse(getUserData().getLULinearImpulse(), body.getWorldCenter(), true);
        body.applyAngularImpulse(getUserData().getHitImpulse(), true);

        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y,screenRectangle.getWidth()/2,screenRectangle.getHeight()/2, screenRectangle.getWidth(),
                screenRectangle.getHeight(),1,1, MathUtils.radiansToDegrees * body.getAngle());


        //draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation)


    }

    public Body getBody()
    {
        return body;
    }

    public Rectangle getScreenRectangle()
    {
        return screenRectangle;
    }


}
