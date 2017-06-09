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
        batch.end();

        //DRAW TOT
        if(TotCoins>0) {
            batch.draw(textureCoinOver, Gdx.graphics, screenRectangle.y + transformToScreenY(2.25f) + coinGone, transformToScreenX(1)/2, transformToScreenY(1)/2, transformToScreenX(1),
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

    public void addCoin(int coin)
    {
        TotCoins+=coin;
    }
}
