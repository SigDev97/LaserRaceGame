package com.sigdev.sasgame.utils;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class BodyUtils {

    public static boolean bodyIsPlayer(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PLAYER;
    }

    public static boolean bodyIsGroundLeft(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND_LEFT;
    }

    public static boolean bodyIsGroundRight(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND_RIGHT;
    }

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();

        switch (userData.getUserDataType()) {
            case PLAYER:
            case ENEMY:
                return body.getPosition().y + userData.getHeight() / 2 < 28;
        }

        return true;
    }

    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }
}
