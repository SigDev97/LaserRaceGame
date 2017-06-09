package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Timer;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.GameActor;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class Player extends GameActor {

    public int going=0;
    private boolean hit=false;

    private final TextureRegion textureRegion,textureCoinOver,textureCoinBack;

    private BitmapFont font;
    private HudManager hudManager;

    private boolean coin=false;
    private int coinValue=0;
    private float coinGone=0;

    private ParticleEffect effect;

    public Player(SasGame game,Body body, BitmapFont font, HudManager hudManager) {
        super(body);

        this.font=font;
        this.hudManager=hudManager;

        textureRegion = new TextureRegion(game.manager.get(Constants.PLAYER_BASE_PATH,Texture.class));
        textureCoinOver=new TextureRegion(game.manager.get(Constants.ENEMY_SQUARE_OVER,Texture.class));
        textureCoinBack=new TextureRegion(game.manager.get(Constants.ENEMY_SQUARE_BACK,Texture.class));

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal(Constants.GREEN_SQUARE), Gdx.files.internal("effects/"));
        effect.getEmitters().first().setPosition(screenRectangle.x,screenRectangle.y);
        effect.setPosition(screenRectangle.x+transformToScreenX(1)/3,screenRectangle.y+ transformToScreenY(1.95f));
        effect.start();
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

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y,screenRectangle.getWidth()/2f,screenRectangle.getHeight()/2f, screenRectangle.getWidth(),
                screenRectangle.getHeight(),1,1, MathUtils.radiansToDegrees * body.getAngle());

        if(!isHit())
        {
            if(coin) {
                batch.draw(textureCoinOver, screenRectangle.x, screenRectangle.y + transformToScreenY(2.25f) + coinGone, transformToScreenX(1)/2, transformToScreenY(1)/2, transformToScreenX(1),
                        transformToScreenY(1), 1, 1, 45);

                batch.draw(textureCoinBack, screenRectangle.x, screenRectangle.y + transformToScreenY(2.25f) + coinGone, transformToScreenX(1)/2, transformToScreenY(1)/2, transformToScreenX(1),
                        transformToScreenY(1), 1, 1, 45);

                if(coinValue==1)
                {
                    font.draw(batch,""+coinValue,screenRectangle.x+transformToScreenX(0.35f),screenRectangle.y + transformToScreenY(3f) + coinGone);
                }
                else
                {
                    font.draw(batch,""+coinValue,screenRectangle.x+transformToScreenX(0.25f),screenRectangle.y + transformToScreenY(3f) + coinGone);
                }
                coinGone+=1f;
            }
        }

        batch.end();
        effect.setPosition(screenRectangle.x+transformToScreenX(1)/3,screenRectangle.y+ transformToScreenY(1.95f));
        batch.begin();
        effect.draw(batch,Gdx.graphics.getDeltaTime());
        batch.end();
        batch.begin();
        if (effect.isComplete() && !hit)
            effect.reset();

    }

    public Body getBody()
    {
        return body;
    }

    public Rectangle getScreenRectangle()
    {
        return screenRectangle;
    }

    public void newCoin(int v)
    {
        coin=true;
        coinValue=v;
        hudManager.addCoin(v);
        coinGone=0;

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                coin=false;
            }
        }, 2f);
    }


}
