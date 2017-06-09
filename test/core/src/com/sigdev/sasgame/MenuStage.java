package com.sigdev.sasgame;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


/**
 * Created by Utente on 05/06/2017.
 */

public class MenuStage extends Stage {

    private Table table;

    public MenuStage()
    {
        Skin skin=new Skin();

        table = new Table();

        //CREARE UNA SKIN
        Label nameLabel = new Label("LaserRaceGame",skin);

        table.add(nameLabel);              // Row 0, column 0.
  // Row 0, column 1.
        table.row();                       // Move to next row.
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
