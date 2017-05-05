package com.sigdev.sasgame.utils;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public abstract class UserData {

    protected UserDataType userDataType;
    protected float width;
    protected float height;

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}