package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.GameActor;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class GroundRight extends GameActor {

    private final TextureRegion textureRegion1,textureRegion2;
    private Sprite back;
    private Rectangle textureRegionBounds;
    private float changeAlpha;
    private float alpha;


    public GroundRight(Body body) {
        super(body);
        textureRegion1 = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_BACK)));
        textureRegion2 = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_OVER)));

        textureRegionBounds = new Rectangle(transformToScreenX(body.getPosition().x-0.75f), transformToScreenY(body.getPosition().y),
                transformToScreenX(getUserData().getWidth()+0.5f), transformToScreenY(getUserData().getHeight()));

        back=new Sprite(textureRegion1);
        back.setBounds(textureRegionBounds.x, textureRegionBounds.y, textureRegionBounds.width, textureRegionBounds.height);

        back.setAlpha(1);
        back.setColor(Color.RED);

        alpha=1;
        changeAlpha=0;

    }

    @Override
    public GroundRightUserData getUserData() {
        return (GroundRightUserData) userData;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

        batch.draw(textureRegion2, textureRegionBounds.x, 0, textureRegionBounds.width, textureRegionBounds.height);


        back.setAlpha(alpha);
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

    }

}
