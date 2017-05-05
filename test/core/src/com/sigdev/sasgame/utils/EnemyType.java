package com.sigdev.sasgame.utils;


public enum EnemyType {

    ENEMY_SQUARE_SMALL(1.5f, 1.5f, 0, Constants.ENEMY_Y, Constants.ENEMY_DENSITY),
    ENEMY_SQUARE_MEDIUM(1.75f, 1.75f, 0, Constants.ENEMY_Y, Constants.ENEMY_DENSITY),
    ENEMY_SQUARE_BIG(2f, 2f, 0, Constants.ENEMY_Y, Constants.ENEMY_DENSITY);


    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    EnemyType(float width, float height, float x, float y, float density) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

}
