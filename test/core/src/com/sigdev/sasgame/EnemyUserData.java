package com.sigdev.sasgame;

import com.badlogic.gdx.math.Vector2;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.UserData;
import com.sigdev.sasgame.utils.UserDataType;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class EnemyUserData extends UserData {


    public EnemyUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.ENEMY;

    }

}