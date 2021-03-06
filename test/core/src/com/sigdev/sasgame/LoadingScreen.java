package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.sigdev.sasgame.utils.Constants;


public class LoadingScreen extends AbstractScreen {

    private Stage stage;


    private Image logo;
    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image screenBg;
    private Image loadingBg;

    private float startX, endX;
    private float percent;

    private Actor loadingBar;

    public LoadingScreen(SasGame game)
    {
        super(game);
    }

    @Override
    public void show() {

        // Tell the manager to load assets for the loading screen
        //game.manager.load("data/loading.pack", TextureAtlas.class);

        //DA FARE CON IL TEXTURE-PACKER---------------------------------------------------------
        game.manager.load(Constants.BACKGROUND_IMAGE_PATH, Texture.class);
        game.manager.load(Constants.ENEMY_SQUARE_BACK,Texture.class);
        game.manager.load(Constants.ENEMY_SQUARE_OVER,Texture.class);
        game.manager.load(Constants.PLAYER_BASE_PATH,Texture.class);


        // Wait until they are finished loading
        game.manager.finishLoading();

        // Initialize the stage where we will place everything
        stage = new Stage();

        // Get our textureatlas from the manager
        //TextureAtlas atlas = game.manager.get("data/loading.pack", TextureAtlas.class);

        // Grab the regions from the atlas and create some images
        /*logo = new Image(atlas.findRegion("libgdx-logo"));
        loadingFrame = new Image(atlas.findRegion("loading-frame"));
        loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));
        screenBg = new Image(atlas.findRegion("screen-bg"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

        // Add the loading bar animation
        Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
        anim.setPlayMode(Animation.LOOP_REVERSED);
        loadingBar = new LoadingBar(anim);

        // Or if you only need a static bar, you can do
        loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        stage.addActor(screenBg);
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingBarHidden);
        stage.addActor(loadingFrame);
        stage.addActor(logo);

        // Add everything to be loaded, for instance:
        // game.manager.load("data/assets1.pack", TextureAtlas.class);
        // game.manager.load("data/assets2.pack", TextureAtlas.class);
        // game.manager.load("data/assets3.pack", TextureAtlas.class);*/
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.manager.update()) { // Load some, will return true if done loading
             // If the screen is touched after the game is done loading, go to the main menu screen
            game.setScreen(new GameScreen(game));
        }

        // Interpolate the percentage to make it more smooth
        /*percent = Interpolation.linear.apply(percent, game.manager.getProgress(), 0.1f);

        // Update positions (and size) to match the percentage
        loadingBarHidden.setX(startX + endX * percent);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setWidth(450 - 450 * percent);
        loadingBg.invalidate();

        // Show the loading screen
        stage.act();
        stage.draw();*/
    }

    @Override
    public void hide() {
        // Dispose the loading assets as we no longer need them
        //game.manager.unload("data/loading.pack");
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
