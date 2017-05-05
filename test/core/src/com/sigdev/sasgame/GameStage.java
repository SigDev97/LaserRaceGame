package com.sigdev.sasgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sigdev.sasgame.utils.BodyUtils;
import com.sigdev.sasgame.utils.WorldUtils;

/**
 * Created by benedetto.sigillo on 13/04/2017.
 */

public class GameStage extends Stage implements ContactListener{
    private static final int VIEWPORT_WIDTH = 15;
    private static final int VIEWPORT_HEIGHT = 25;

    private World world;
    private GroundLeft groundLeft;
    private GroundRight groundRight;
    private Player player;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    public OrthographicCamera camera;
    public Viewport viewport;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide,screenLeftSide;
    private Vector3 touchPoint;

    private FPSLogger fps;

    public GameStage() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);

        renderer = new Box2DDebugRenderer();
        setupCamera();
        setUpBackground();
        setupGround();
        setupPlayer();

        createEnemy();
        setupTouchControlAreas();

        fps=new FPSLogger();
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        viewport=new ScalingViewport(Scaling.stretch,VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
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
        player=new Player(WorldUtils.createPlayer(world));
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
        addActor(new Background());
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation


    }

    private void update(Body body) {
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
        //renderer.render(world, camera.combined);
        fps.log();
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
