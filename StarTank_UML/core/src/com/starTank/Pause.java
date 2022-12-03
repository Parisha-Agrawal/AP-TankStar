package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
public class Pause implements Screen {


    private final Shoot game;
    private GameScreen gameScreen;
    private final Texture SaveButtonImage;
    private final Texture ResumeButtonImage;
    private final Texture ExitButtonImage;
    private final Texture PauseTxt;
    private final Music EnvironmentWar;
    private final Rectangle SaveButton;
    private final Rectangle ResumeButton;
    private final Rectangle ExitButton;
    private final Rectangle Pause;

    private final TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public Pause(final Shoot game, GameScreen gameScreen) {
        this.game = game;
        this.gameScreen = gameScreen;
        SaveButtonImage = new Texture(Gdx.files.internal("startButton.png"));
        ResumeButtonImage = new Texture(Gdx.files.internal("ResumeButton.png"));
        ExitButtonImage = new Texture(Gdx.files.internal("ExitButton.png"));
        PauseTxt = new Texture(Gdx.files.internal("PauseTxt.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("Background2.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // create a Rectangle to logically represent the tanks
        SaveButton = new Rectangle();
        SaveButton.x = 800f / 3 - 30; // center the bucket horizontally
        SaveButton.y = 158; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        SaveButton.width = 300;
        SaveButton.height = 300;

        ExitButton = new Rectangle();
        ExitButton.x = 2* 800f / 3 - 300; // center the bucket horizontally
        ExitButton.y = 10; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        ExitButton.width = 310;
        ExitButton.height = 300;

        ResumeButton = new Rectangle();
        ResumeButton.x = 3* 800f / 3 - 570; // center the bucket horizontally
        ResumeButton.y = 70; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        ResumeButton.width = 310;
        ResumeButton.height = 300;

        Pause = new Rectangle();
        Pause.x = 3* 800f / 3 - 620; // center the bucket horizontally
        Pause.y = 240; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        Pause.width = 410;
        Pause.height = 300;

    }

    @Override
    public void show() {
        EnvironmentWar.play();
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);
//        atlas = new TextureAtlas()
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Press 1, 2 or 3", 330, 350);
        game.batch.draw(SaveButtonImage, SaveButton.x, SaveButton.y, SaveButton.width, SaveButton.height);
        game.batch.draw(ExitButtonImage, ExitButton.x, ExitButton.y, ExitButton.width, ExitButton.height);
        game.batch.draw(ResumeButtonImage, ResumeButton.x, ResumeButton.y, ResumeButton.width, ResumeButton.height);
        game.batch.draw(PauseTxt, Pause.x, Pause.y, Pause.width, Pause.height);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            game.setScreen(new SavedGames(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            game.setScreen(this.gameScreen);
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            game.setScreen(new HomePage(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        SaveButtonImage.dispose();
        ExitButtonImage.dispose();
        ResumeButtonImage.dispose();
        EnvironmentWar.dispose();
        PauseTxt.dispose();
    }

}


