package com.sigdev.sasgame;

import com.sigdev.sasgame.utils.UserData;
import com.sigdev.sasgame.utils.UserDataType;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

public class GroundRightUserData extends UserData {
    public GroundRightUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.GROUND_RIGHT;
    }
}
