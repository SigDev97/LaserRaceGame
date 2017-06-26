package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sigdev.sasgame.utils.Constants;

/**
 * Created by Utente on 09/06/2017.
 */

public class HudManager {

    private Batch batch;
    private BitmapFont font;
    private int TotCoins;

    private GlyphLayout glyphLayout;

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

        glyphLayout = new GlyphLayout();


        batch.begin();
        font.draw(batch,String.format("%.1f", space),transformToScreen(0.5f), transformToScreen(24.6f));
        float spaceR= 2f;
        if(space>10f)
        {
            spaceR= 2.2f;
        }
        font.draw(batch,"byt",transformToScreen(spaceR), transformToScreen(24.6f));


        font.draw(batch,String.format("%.2f", speed),transformToScreen(0.5f),transformToScreen(0.5f));
        float speedR= 2.5f;
        if(speed>10f)
        {
            speedR= 3f;
        }
        font.draw(batch,"bit/s",transformToScreen(speedR), transformToScreen(0.5f));

        //DRAW TOT
        if(TotCoins>0) {
            batch.draw(textureCoinOver, transformToScreen(12.5f), transformToScreen(23f), transformToScreen(1), transformToScreen(1), transformToScreen(1.5f),
                    transformToScreen(1.5f), 1, 1, 45);

            batch.draw(textureCoinBack, transformToScreen(12.5f), transformToScreen(23f), transformToScreen(1), transformToScreen(1), transformToScreen(1.5f),
                    transformToScreen(1.5f), 1, 1, 45);

            glyphLayout.setText(font,""+TotCoins);
            float w = glyphLayout.width;
            font.draw(batch, glyphLayout, transformToScreen(13.5f)-(w/2), transformToScreen(23.85f));
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

}
