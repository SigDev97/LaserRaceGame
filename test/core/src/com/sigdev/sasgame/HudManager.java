package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sigdev.sasgame.utils.Constants;

/**
 * Created by Utente on 09/06/2017.
 */

public class HudManager {

    private Batch batch;
    private BitmapFont font;
    private int TotCoins;

    private final TextureRegion textureCoinOver,textureCoinBack;

    public HudManager (Batch batch, BitmapFont font) {
        this.batch=batch;
        this.font=font;
        TotCoins = 0;

        textureCoinOver=new TextureRegion(new Texture(Gdx.files.internal(Constants.ENEMY_SQUARE_OVER)));
        textureCoinBack=new TextureRegion(new Texture(Gdx.files.internal(Constants.ENEMY_SQUARE_BACK)));
    }

    public void draw(float space, float speed) {

        //DRAW SPACE AND SPEED
        batch.begin();
        font.draw(batch,String.format("%.2f", space)+" m",20, Gdx.graphics.getHeight()-20);
        font.draw(batch,String.format("%.2f", speed)+" bit/s",20,30);

        //DRAW TOT
        if(TotCoins>0) {
            batch.draw(textureCoinOver, Gdx.graphics.getWidth()-transformToScreenX(2.5f), Gdx.graphics.getHeight()-transformToScreenY(2f), transformToScreenX(1), transformToScreenY(1), transformToScreenX(1.5f),
                    transformToScreenY(1.5f), 1, 1, 45);

            batch.draw(textureCoinBack, Gdx.graphics.getWidth()-transformToScreenX(2.5f), Gdx.graphics.getHeight()-transformToScreenY(2f), transformToScreenX(1), transformToScreenY(1), transformToScreenX(1.5f),
                    transformToScreenY(1.5f), 1, 1, 45);





            if(TotCoins==1)
            {
                font.draw(batch,""+TotCoins,Gdx.graphics.getWidth()-transformToScreenX(1.65f), Gdx.graphics.getHeight()-transformToScreenY(1.15f));
            }
            else if(TotCoins<10)
            {
                font.draw(batch,""+TotCoins,Gdx.graphics.getWidth()-transformToScreenX(1.725f), Gdx.graphics.getHeight()-transformToScreenY(1.15f));
            }
            else
            {
                font.draw(batch,""+TotCoins,Gdx.graphics.getWidth()-transformToScreenX(1.90f), Gdx.graphics.getHeight()-transformToScreenY(1.15f));
            }
        }
        batch.end();
    }

    public void addCoin(int coin)
    {
        TotCoins+=coin;
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
