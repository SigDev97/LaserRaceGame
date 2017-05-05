package com.sigdev.sasgame.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by benedetto.sigillo on 13/04/2017.
 */

public class Constants {
    public static final int APP_WIDTH = 480;
    public static final int APP_HEIGHT = 800;
    public static final float WORLD_TO_SCREEN = 32;

    public static final String BACKGROUND_IMAGE_PATH = "background.png";

    public static final String GROUND_BACK = "ground/laser.png";
    public static final String GROUND_OVER = "ground/laserOverlayStatic.png";

    public static final String ENEMY_SQUARE_BACK = "enemy/enemy_square_back.png";
    public static final String ENEMY_SQUARE_OVER = "enemy/enemy_square_over.png";

    public static final String PLAYER_BASE_PATH = "player_base.png";

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);

    public static final float GROUND_LEFT_X = 0f;
    public static final float GROUND_LEFT_Y = 0;
    public static final float GROUND_RIGHT_X = 15f;
    public static final float GROUND_RIGHT_Y = 0;

    public static final float GROUND_WIDTH = 0.5f;
    public static final float GROUND_HEIGHT = 50f;
    public static final float GROUND_DENSITY = 0f;

    /////////////////////////////////////////////////

    public static final float PLAYER_X = 6.5f;
    public static final float PLAYER_Y = 22;
    public static final float PLAYER_WIDTH = 1f;
    public static final float PLAYER_HEIGHT = 2f;
    public static float PLAYER_DENSITY = 0.5f;

    public static final float PLAYER_GRAVITY_SCALE = 3f;
    public static final Vector2 PLAYER_RIGHT_LINEAR_IMPULSE = new Vector2(11f, 0f);
    public static final Vector2 PLAYER_LEFT_LINEAR_IMPULSE = new Vector2(-11f, 0f);

    public static final float HIT_IMPULSE = 14f;
    public static final Vector2 PLAYER_RU_LINEAR_IMPULSE = new Vector2(14f, 7f);
    public static final Vector2 PLAYER_LU_LINEAR_IMPULSE = new Vector2(-14f, 7f);

    ////////////////////////////////////////////////////

    public static final float ENEMY_RX = 14.8f;
    public static final float ENEMY_LX = 0.2f;
    public static final float ENEMY_Y = -2f;
    public static final float ENEMY_DENSITY = PLAYER_DENSITY;




}
