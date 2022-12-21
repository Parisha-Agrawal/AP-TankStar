package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    private final Shoot game;
    public static final String TITLE="TANK STAR";
    private Texture tank1Image;
    private Texture tank2Image;
    private Texture pauseButtonImage;
    private final TextureRegion backgroundTexture;
    private final Sound shootSound;
    private final Music EnvironmentWar;
    private Texture arrowImage;
    private Array<Rectangle> arrowpath;
    private long lastArrowTime;
    private OrthographicCamera camera;
    private final Rectangle tank1;
    private final Rectangle tank2;
    private final Rectangle pauseButton;
    private final Player p1;
    private final Player p2;

    public GameScreen(final Shoot game, Player p1, Player p2) {
        this.game = game;

        this.p1 = p1;
        this.p2 = p2;

        pauseButtonImage = new Texture(Gdx.files.internal("PauseButton.png"));
        arrowImage = new Texture(Gdx.files.internal("arrow.png"));
//        Texture backgroundImage = new Texture(Gdx.files.internal("landscapebg.jpg"));
        Texture backgroundImage = new Texture(Gdx.files.internal("gamescreen2.jpeg"));
//        backgroundTexture = new TextureRegion(backgroundImage, -5, -5, 356, 271);
        backgroundTexture = new TextureRegion(backgroundImage, 80, 50, 856, 551);

        // load the sound effect and the background
        shootSound = Gdx.audio.newSound(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the tank
        tank1 = new Rectangle();
        tank1.x = p1.getInfo().getTank().getPositionX();
        tank1.y = p1.getInfo().getTank().getPositionY();
        tank1.width = 70;
        tank1.height = 50;

        tank2 = new Rectangle();
        tank2.x = p2.getInfo().getTank().getPositionX();
        tank2.y = p2.getInfo().getTank().getPositionY();
        tank2.width = 70;
        tank2.height = 50;

        pauseButton = new Rectangle();
        pauseButton.x = 800 - 30;
        pauseButton.y = 450;
        pauseButton.width = 20;
        pauseButton.height = 20;

        arrowpath = new Array<>();
        spawnArrow();

    }

    public GameScreen(final Shoot game, GameInfo gameInfo) {
        this.game = game;

        this.p1 = new Player("Player 1",gameInfo.getP1GameInfo());
        this.p2 = new Player("Player 2",gameInfo.getP2GameInfo());

        pauseButtonImage = new Texture(Gdx.files.internal("PauseButton.png"));

//        Texture backgroundImage = new Texture(Gdx.files.internal("landscapebg.jpg"));
        Texture backgroundImage = new Texture(Gdx.files.internal("gamescreen2.jpeg"));
//        backgroundTexture = new TextureRegion(backgroundImage, -5, -5, 356, 271);
        backgroundTexture = new TextureRegion(backgroundImage, 80, 50, 856, 551);

        // load the sound effect and the background
        shootSound = Gdx.audio.newSound(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the tank
        tank1 = new Rectangle();
        tank1.x = p1.getInfo().getTank().getPositionX();
        tank1.y = p1.getInfo().getTank().getPositionY();
        tank1.width = 70;
        tank1.height = 50;

        tank2 = new Rectangle();
        tank2.x = p2.getInfo().getTank().getPositionX();
        tank2.y = p2.getInfo().getTank().getPositionY();
        tank2.width = 70;
        tank2.height = 50;

        pauseButton = new Rectangle();
        pauseButton.x = 800 - 30;
        pauseButton.y = 450;
        pauseButton.width = 20;
        pauseButton.height = 20;


    }

    private void spawnArrow() {
        Rectangle arrows = new Rectangle();
        arrows.x=-100;
        arrows.y = 550;
        arrows.width = 14;
        arrows.height = 14;
        arrowpath.add(arrows);
        lastArrowTime = TimeUtils.nanoTime();
    }
    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        debugRenderer.render(world,camera.combined);
//        world.step(TIMESTEP,VELOCITYITERATIONS,POSITIONITERATIONS);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        tank1Image = new Texture(Gdx.files.internal(p1.getInfo().getTank().getName()));
        tank2Image = new Texture(Gdx.files.internal(p2.getInfo().getTank().getName()));

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Player1 Health: " + p1.getInfo().getHealth(), 0, 480);
        game.font.draw(game.batch, "Player2 Health: " + p2.getInfo().getHealth(), 600, 480);
        game.batch.draw(tank1Image, tank1.x, tank1.y, tank1.width, tank1.height);
        game.batch.draw(tank2Image, tank2.x, tank2.y, tank2.width, tank2.height);
        game.batch.draw(pauseButtonImage, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height);
        for (Rectangle arrow1 : arrowpath) {
            game.batch.draw(arrowImage,  arrow1.y-70, arrow1.x-70);
        }
        game.batch.end();

//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            tank1.x = touchPos.x - 32;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            p1.getInfo().getTank().setPositionX((int) (p1.getInfo().getTank().getPositionX() - (200 * Gdx.graphics.getDeltaTime())));
            tank1.x = p1.getInfo().getTank().getPositionX();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            p1.getInfo().getTank().setPositionX((int) (p1.getInfo().getTank().getPositionX() + (200 * Gdx.graphics.getDeltaTime())));
            tank1.x = p1.getInfo().getTank().getPositionX();

        if (tank1.x < 0) {
            p1.getInfo().getTank().setPositionX(0);
            tank1.x = p1.getInfo().getTank().getPositionX();
        }
        if (tank1.x > 800 - 174) {
            p1.getInfo().getTank().setPositionX(800-174);
            tank1.x = p1.getInfo().getTank().getPositionX();
        }
        for (int i=0; i<4;i++){
            if (tank1.x < 0)
                tank1.x = 0;
            if (tank1.x >=60*i && tank1.x<60*i+30)
                tank1.y=100+30*i;
        }
        for(int j=1; j<4;j++) {
            if (tank1.x >= 180 + 60 * j && tank1.x < 180 + 60 * j + 30) {
                tank1.y = 220- 50 * j;
            }
            if (tank1.x > 800 - 174)
                tank1.x = 800 - 174;
        }
        for (int i=0; i<3;i++){
            if (tank1.x < 0)
                tank1.x = 0;
            if (tank1.x >=360+60*i && tank1.x<360+60*i+30)
                tank1.y=180+40*i;
        }
        for(int j=1; j<4;j++) {
            if (tank1.x >= 560 + 60 * j && tank1.x < 560 + 60 * j + 30) {
                tank1.y = 300- 50 * j;
            }
            if (tank1.x > 800 - 174)
                tank1.x = 800 - 174;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            p2.getInfo().getTank().setPositionX((int) (p2.getInfo().getTank().getPositionX() - (200 * Gdx.graphics.getDeltaTime())));
            tank2.x = p2.getInfo().getTank().getPositionX();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            p2.getInfo().getTank().setPositionX((int) (p2.getInfo().getTank().getPositionX() + (200 * Gdx.graphics.getDeltaTime())));
            tank2.x = p2.getInfo().getTank().getPositionX();

        if (tank2.x < 0){
            p2.getInfo().getTank().setPositionX(0);
            tank2.x = p2.getInfo().getTank().getPositionX();
        }
        if (tank2.x > 800 - 84){
            p2.getInfo().getTank().setPositionX(800-84);
            tank2.x = p2.getInfo().getTank().getPositionX();
        }
        for (int i=0; i<3;i++){
            if (tank2.x < 0)
                tank2.x = 0;

            if (tank2.x >=560+60*i && tank2.x<560+60*i+40) {
                if(tank2.x>800-130 )
                    tank2.y=85;
                else
                    tank2.y = 180 - 30 * i;
            }
            if (tank2.x >=360+60*i && tank2.x<360+60*i+40)
                tank2.y=180+40*i;
            if (tank2.x > 800 - 84)
                tank2.x = 800 - 84;
        }
        if (TimeUtils.nanoTime() - lastArrowTime > 1000000000)
            spawnArrow();
        Iterator<Rectangle> iter = arrowpath.iterator();
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            while (iter.hasNext()) {
                Rectangle arrows = iter.next();
                arrows.y -= 200 * Gdx.graphics.getDeltaTime();
                if (arrows.y + 64 < 0)
                    iter.remove();
                for(int j=1;j<3;j++) {
                    if (arrows.y >= 200 * j && arrows.y < 200 * j + 50)
                        arrows.x = 70 * j;
//                    arrows.height+=j*140;
                }
                for(int k=1;k<3;k++){
                    if (arrows.y>= 200*k && arrows.y<200*k+50)
                        arrows.x= 10*k;
                }

                if (arrows.overlaps(tank1)) {
//                    player1Health--;
                    iter.remove();
                }
            }}

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= 800 - 30 - 5 && touchPos.x <= 800 - 30 + 20 && touchPos.y >= 450 - 5 && touchPos.y <=  450 + 20){
                game.setScreen(new Pause(game,p1.getInfo(),p2.getInfo()));
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
//        camera.viewportWidth = width / 25;
//        camera.viewportHeight = height / 25;
//        camera.update();
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        EnvironmentWar.play();

//        world = new World(new Vector2(0,-9.81f), true);
//        debugRenderer = new Box2DDebugRenderer();
//        camera = new OrthographicCamera(Gdx.graphics.getWidth()/25, Gdx.graphics.getHeight() / 25);

        // ball
        // body definition
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(0,1);

//        CircleShape ballShape = new CircleShape();
//        ballShape.setRadius(.5f);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = ballShape;
//        fixtureDef.density = 2.5f;
//        fixtureDef.friction = .25f;
//        fixtureDef.restitution = .75f;
//
//        world.createBody(bodyDef).createFixture(fixtureDef);
//        ballShape.dispose();


        // Ground
        // body definition
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        bodyDef.position.set(0,0);

//        ChainShape groundShape = new ChainShape();
//        groundShape.createChain(new Vector2[] {new Vector2(-500,0) , new Vector2(500, 0)});
//
//        fixtureDef.shape = groundShape;
//        fixtureDef.friction = .5f;
//        fixtureDef.restitution = 0;
//
//        world.createBody(bodyDef).createFixture(fixtureDef);
//        groundShape.dispose();
    }

    @Override
    public void hide() {
        Gdx.app.log(TITLE, "hide()");
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        tank1Image.dispose();
        tank2Image.dispose();
        shootSound.dispose();
        EnvironmentWar.dispose();
    }

}
