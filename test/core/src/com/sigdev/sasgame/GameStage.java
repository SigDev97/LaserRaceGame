package com.sigdev.sasgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sigdev.sasgame.utils.BodyEditorLoader;
import com.sigdev.sasgame.utils.BodyUtils;
import com.sigdev.sasgame.utils.Constants;
import com.sigdev.sasgame.utils.SmartFontGenerator;
import com.sigdev.sasgame.utils.WorldUtils;

import box2dLight.PointLight;
import box2dLight.RayHandler;

/**
 * Created by benedetto.sigillo on 13/04/2017.
 */

public class GameStage extends Stage implements ContactListener{

    private SasGame game;

    //SCREEN
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    public OrthographicCamera camera;
    public Viewport viewport;

    private Rectangle screenRightSide,screenLeftSide;
    private Vector3 touchPoint;

    private HudManager hudManager;
    private BitmapFont font;

    private Box2DDebugRenderer renderer;


    //OBJECTS
    public World world;

    private GroundLeft groundLeft;
    private GroundRight groundRight;
    private Player player;


    //MOVIMENTO E VARIABILI
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private Background background;
    private Vector2 speed;
    private float space;
    private int s=0;

    //ALTRO
    private FPSLogger fps;



    public GameStage(SasGame game) {

        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));

        this.game=game;

        //INIZIALIZATION WORLD
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        renderer = new Box2DDebugRenderer();

        //SCREEN
        speed=new Vector2(0f,0f);
        space=0;
        initFont();
        hudManager = new HudManager(getBatch(),font);
        setupCamera();
        setUpBackground();

        //OBJECTS
        setupGround();
        setupPlayer();

        //CONTROLS
        createEnemy();
        setupTouchControlAreas();

        //TESTS
        fps=new FPSLogger();

        ///PROVA IMPORTAZIONE MODELLO 2D
    }

    private void initFont()
    {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        SmartFontGenerator fontGen = new SmartFontGenerator();
        FileHandle exoFile = Gdx.files.internal(Constants.FONT);
        font = fontGen.createFont(exoFile, "exo-small", 22);
        font.getData().setScale(1+(25/Gdx.graphics.getHeight()));

    }

    private void setupCamera() {
        /*camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        viewport=new ScalingViewport(Scaling.stretch,VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();*/

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();


    }



    private void setupGround()
    {
        groundLeft = new GroundLeft(WorldUtils.createGroundLeft(world));
        groundRight = new GroundRight(WorldUtils.createGroundRight(world));
        addActor(groundLeft);
        addActor(groundRight);
    }

    private void setupPlayer()
    {
        player=new Player(game,WorldUtils.createPlayer(world),font, hudManager);
        addActor(player);
    }

    private void setupTouchControlAreas()
    {
        touchPoint = new Vector3();
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    private void setUpBackground() {
        background = new Background(game);
        addActor(background);
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(game,WorldUtils.createEnemy(world),speed,player);
        addActor(enemy);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //DELETE OLD BODYS
        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            updateBody(body);
        }

        // Fixed timestep
        accumulator += delta;
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation


        //SPEED & SPACE

        if(speed.y<7.5)//START
        {
            speed.y=speed.y+0.0075f;//Temporany speed increase method
            if(s>10)
            {
                background.setSpeed(background.getSpeed()+1);
                s=0;
            }
            space+= (speed.y*(delta*7.5f))/100;
        }
        else
        {
            speed.y=speed.y+0.0020f;//Temporany speed increase method
            if(s>30)
            {
                background.setSpeed(background.getSpeed()+1);
                s=0;
            }
            space+= (speed.y*(delta*10f))/100;
        }
        //Temporany space increase method

        s++;


    }

    private void updateBody(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !player.isHit()) {
                createEnemy();
            }

            world.destroyBody(body);
        }
    }

    @Override
    public void draw() {
        super.draw();
        hudManager.draw(space, speed.y);
        getCamera().combined.scale(getCamera().viewportWidth,getCamera().viewportHeight,0f);
        renderer.render(world,getCamera().view);

    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            player.right();
        }
        else if (leftSideTouched(touchPoint.x, touchPoint.y))
        {
            player.left();
        }

        return super.touchDown(x, y, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }

    /**
     * Helper function to get the actual coordinates in my world
     * @param x
     * @param y
     */
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsPlayer(b)))
        {
            player.hit();
        }
        else if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGroundLeft(b)) ||
                (BodyUtils.bodyIsGroundLeft(a) && BodyUtils.bodyIsPlayer(b)))
        {
            player.going=0;
            if(a.getPosition().x > 7 && b.getPosition().x > 7)
            {
                player.hitRight();
            }
            else if(a.getPosition().x < 7 && b.getPosition().x < 7)
            {
                player.hitLeft();

            }
        }
        else if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGroundRight(b)) ||
                (BodyUtils.bodyIsGroundRight(a) && BodyUtils.bodyIsPlayer(b)))
        {
            player.going=0;
            if(a.getPosition().x > 7 && b.getPosition().x > 7)
            {
                player.hitRight();
            }
            else if(a.getPosition().x < 7 && b.getPosition().x < 7)
            {
                player.hitLeft();

            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
