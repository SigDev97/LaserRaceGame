package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private Sprite back;

    private float changeAlpha;
    private float alpha;

    private Vector2 speed;


    private Player player;
    private float distance = 100;
    private Boolean coins=false;

    private BitmapFont font = new BitmapFont();
    private String string;


    public Enemy(Body body,Vector2 speed,Player player) {

        //DRAW//////////////////////////////////
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
        /////////////////////////////////////////


        ///COINS////////////////////////////////
        this.player=player;
        font.getData().setScale(1.25f);
        string="";
        /////////////////////////////////////////

        this.speed=speed;

    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(speed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        //DRAW/////////////////////////////////////////////////
        batch.draw(textureRegion2, screenRectangle.x, screenRectangle.y+10, screenRectangle.getWidth(),
                screenRectangle.getHeight());

        back.setBounds(screenRectangle.x, screenRectangle.y+10, screenRectangle.getWidth(),
                screenRectangle.getHeight());
        back.draw(batch);

        //NEON EFFECT//////////////////////////////////////////
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

        //COINS////////////////////////////////////////////////
        font.draw(batch, string, Gdx.graphics.getWidth()-240, 20);

        if(body.getPosition().y>=16 && body.getPosition().y<=24)
        {
            float positionPlayer = player.getBody().getPosition().x;
            float positionEnemy = body.getPosition().x;


            if (positionPlayer>positionEnemy)
            {
                if(positionPlayer-positionEnemy<distance)
                {
                    distance=positionPlayer-positionEnemy;
                }
            }
            else
            {
                if(positionEnemy-positionPlayer<distance)
                {
                    distance=positionEnemy-positionPlayer;
                }
            }

        }

        if(body.getPosition().y>24 && !coins)
        {
            coins=true;
            if (!player.isHit())
            {
                Gdx.app.log("Closest Distance"," "+distance);
                string="Closest Distance="+distance;
            }

        }


    }

}
