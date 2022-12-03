package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private final Shoot game;
    public static final String TITLE="TANK STAR";
    private Texture tank1Image;
    private Texture tank2Image;
    private Texture pauseButtonImage;
    private final TextureRegion backgroundTexture;
    private final Sound shootSound;
    private final Music EnvironmentWar;
    private final OrthographicCamera camera;
    private final Rectangle tank1;
    private final Rectangle tank2;
    private final Rectangle pauseButton;
    private final Tank p1Tank;
    private final Tank p2Tank;

    public GameScreen(final Shoot game, Tank p1Tank, Tank p2Tank) {
        this.game = game;
        System.out.println(p1Tank.getName());
        System.out.println(p2Tank.getName());

        this.p1Tank = p1Tank;
        this.p2Tank = p2Tank;

        pauseButtonImage = new Texture(Gdx.files.internal("PauseButton.png"));

//        Texture backgroundImage = new Texture(Gdx.files.internal("landscapebg.jpg"));
        Texture backgroundImage = new Texture(Gdx.files.internal("gamescreen2.jpeg"));
        //backgroundTexture = new TextureRegion(backgroundImage, -5, -5, 356, 271);
        backgroundTexture = new TextureRegion(backgroundImage, 80, 50, 856, 551);

        // load the drop sound effect and the rain background "music"
        shootSound = Gdx.audio.newSound(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the tank
        tank1 = new Rectangle();
        tank1.x = 800f/4 - 200;
        tank1.y = 82;
        tank1.width = 70;
        tank1.height = 50;

        tank2 = new Rectangle();
        tank2.x = 2* 800f/3 +5000;
        tank2.y = 100;
        tank2.width = 70;
        tank2.height = 50;

        pauseButton = new Rectangle();
        pauseButton.x = 800 - 30;
        pauseButton.y = 450;
        pauseButton.width = 20;
        pauseButton.height = 20;

    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        tank1Image = new Texture(Gdx.files.internal(p1Tank.getName()));
        tank2Image = new Texture(Gdx.files.internal(p2Tank.getName()));

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        int player1Health = 20;
        game.font.draw(game.batch, "Player1 Health: " + player1Health, 0, 480);
        int player2Health = 20;
        game.font.draw(game.batch, "Player2 Health: " + player2Health, 600, 480);
        game.batch.draw(tank1Image, tank1.x, tank1.y, tank1.width, tank1.height);
        game.batch.draw(tank2Image, tank2.x, tank2.y, tank2.width, tank2.height);
        game.batch.draw(pauseButtonImage, pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height);
        game.batch.end();

//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            tank1.x = touchPos.x - 32;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            tank1.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            tank1.x += 200 * Gdx.graphics.getDeltaTime();

        if (tank1.x < 0)
            tank1.x = 0;
        if (tank1.x > 800 - 174)
            tank1.x = 800 - 174;


        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            tank2.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            tank2.x += 200 * Gdx.graphics.getDeltaTime();

        if (tank2.x < 0)
            tank2.x = 0;
        if (tank2.x > 800 - 84)
            tank2.x = 800 - 84;

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= 800 - 30 - 5 && touchPos.x <= 800 - 30 + 20 && touchPos.y >= 450 - 5 && touchPos.y <=  450 + 20){
                game.setScreen(new Pause(game,this));
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        EnvironmentWar.play();
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
