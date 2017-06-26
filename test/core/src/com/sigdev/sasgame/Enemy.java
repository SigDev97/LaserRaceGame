package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.GameActor;

import aurelienribon.bodyeditor.BodyEditorLoader;

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


    public Enemy(SasGame game,Body body,Vector2 speed,Player player) {

        //DRAW//////////////////////////////////
        super(body);
        textureRegion1 = new TextureRegion(game.manager.get(Constants.ENEMY_SQUARE_BACK,Texture.class));
        textureRegion2 = new TextureRegion(game.manager.get(Constants.ENEMY_SQUARE_OVER,Texture.class));
        back=new Sprite(textureRegion1);
        back.setBounds(screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(),
                screenRectangle.getHeight());

        back.setAlpha(1f);
        back.setColor(Color.ORANGE);
        alpha=1;
        changeAlpha=0;
        /////////////////////////////////////////

        ///COINS////////////////////////////////
        this.player=player;
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

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);


        //DRAW/////////////////////////////////////////////////
        batch.draw(textureRegion2, screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());

        back.setBounds(screenRectangle.x, screenRectangle.y, screenRectangle.getWidth(), screenRectangle.getHeight());
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

        if(body.getPosition().y>=16 && body.getPosition().y<=23)
        {
            distanceLeftAngle();
            distanceRightAngle();

        }

        if(body.getPosition().y>23 && !coins)
        {
            coins=true;
            if (!player.isHit())
            {

                if(distance<15f) {
                    player.newCoin(5);
                }
                else if(distance<25f)
                {
                    player.newCoin(3);
                }

                else if(distance<50f)
                {
                    player.newCoin(1);
                }

            }
        }


    }

    private void distanceLeftAngle()
    {
        Vector2 position1 = player.getBody().getPosition();
        Vector2 position2 = body.getPosition();

        position1.x=transformToScreen(position1.x);
        position1.x=position1.x-(player.getScreenRectangle().width/2);

        position2.x=transformToScreen(position2.x);
        position2.x=position2.x+(screenRectangle.width/2);


        position1.y=transformToScreen(position1.y);
        position1.y=position1.y-(player.getScreenRectangle().height/2);

        position2.y=transformToScreen(position2.y);
        position2.y=position2.y+(screenRectangle.height/2);

        float prov_distance = position1.dst(position2);

        if(prov_distance<distance && prov_distance>0)
        {
            distance=prov_distance;
        }
    }

    private void distanceRightAngle()
    {
        Vector2 position1 = player.getBody().getPosition();
        Vector2 position2 = body.getPosition();

        position1.x=transformToScreen(position1.x);
        position1.x=position1.x+(player.getScreenRectangle().width/2);

        position2.x=transformToScreen(position2.x);
        position2.x=position2.x-(screenRectangle.width/2);

        position1.y=transformToScreen(position1.y);
        position1.y=position1.y-(player.getScreenRectangle().height/2);

        position2.y=transformToScreen(position2.y);
        position2.y=position2.y+(screenRectangle.height/2);

        float prov_distance = position1.dst(position2);

        if(prov_distance<distance && prov_distance>0)
        {
            distance=prov_distance;
        }
    }

}
