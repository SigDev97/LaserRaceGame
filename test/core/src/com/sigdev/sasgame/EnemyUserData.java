package com.sigdev.sasgame;

import com.badlogic.gdx.math.Vector2;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.UserData;
import com.sigdev.sasgame.utils.UserDataType;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class EnemyUserData extends UserData {

    private Vector2 linearVelocity;

    public EnemyUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.ENEMY;
        linearVelocity = new Vector2(0f,25f);//VELOCITA DA CAMBIARE, PROGRESSIVA!
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

}