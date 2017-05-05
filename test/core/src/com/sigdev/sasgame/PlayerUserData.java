package com.sigdev.sasgame;

import com.badlogic.gdx.math.Vector2;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.UserData;
import com.sigdev.sasgame.utils.UserDataType;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class PlayerUserData extends UserData {

    private Vector2 RightLinearImpulse,LeftLinearImpulse,RULinearImpulse,LULinearImpulse;

    public PlayerUserData(float width, float height) {
        super(width,height);
        RightLinearImpulse = Constants.PLAYER_RIGHT_LINEAR_IMPULSE;
        LeftLinearImpulse = Constants.PLAYER_LEFT_LINEAR_IMPULSE;

        RULinearImpulse = Constants.PLAYER_RU_LINEAR_IMPULSE;
        LULinearImpulse = Constants.PLAYER_LU_LINEAR_IMPULSE;

        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getRightLinearImpulse() {
        return RightLinearImpulse;
    }

    public Vector2 getLeftLinearImpulse() {
        return LeftLinearImpulse;
    }

    public Vector2 getRULinearImpulse() {
        return RULinearImpulse;
    }

    public Vector2 getLULinearImpulse() {
        return LULinearImpulse;
    }

    public float getHitImpulse() {
        return Constants.HIT_IMPULSE;
    }

    //SETTER??

}
