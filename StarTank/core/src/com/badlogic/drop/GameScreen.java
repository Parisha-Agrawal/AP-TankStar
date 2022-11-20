package com.badlogic.drop;

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
    private final Texture tank1Image;
    private final Texture tank2Image;

    private final TextureRegion backgroundTexture;
    private final Sound dropSound;
    private final Music EnvironmentWar;
    private final OrthographicCamera camera;
    private final Rectangle tank1;
    private final Rectangle tank2;
    private final int player1Health = 20;
    private final int player2Health = 20;
    public GameScreen(final Shoot game) {
        this.game = game;

        // load the images for the droplet and the bucket, 64x64 pixels each
        tank1Image = new Texture(Gdx.files.internal("AbramsTank.png"));
        tank2Image = new Texture(Gdx.files.internal("TigerBowUpFlipGameImage.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1376, 771);

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        EnvironmentWar.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the bucket
        tank1 = new Rectangle();
        tank1.x = 800f/4 - 70; // center the bucket horizontally
        tank1.y = 222; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        tank1.width = 74;
        tank1.height = 64;

        tank2 = new Rectangle();
        tank2.x = 2* 800f/3 +120; // center the bucket horizontally
        tank2.y = 240; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        tank2.width = 64;
        tank2.height = 34;

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

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Player1 Health: " + player1Health, 0, 480);
        game.font.draw(game.batch, "Player2 Health: " + player2Health, 600, 480);
        game.batch.draw(tank1Image, tank1.x, tank1.y, tank1.width, tank1.height);
        game.batch.draw(tank2Image, tank2.x, tank2.y, tank2.width, tank2.height);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            tank1.x = touchPos.x - 32;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)){
            game.setScreen(new Pause(game));
            dispose();
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
        dropSound.dispose();
        EnvironmentWar.dispose();
    }

}
