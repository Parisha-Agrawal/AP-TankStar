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
public class SavedGames implements Screen {

    private final Shoot game;
    private final Texture time1Image;
    private final Texture time2Image;
    private final Texture SavedGamesTxt;
    private final Music EnvironmentWar;
    private final Rectangle time1;
    private final Rectangle time2;
    private final Rectangle SavedGames;

    private final TextureRegion backgroundTexture;
    private final OrthographicCamera camera;

    public SavedGames(final Shoot game) {
        this.game = game;
        time1Image = new Texture(Gdx.files.internal("4_00.png"));
        time2Image = new Texture(Gdx.files.internal("4_20.png"));
        SavedGamesTxt = new Texture(Gdx.files.internal("SavedGamesTxt.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("bgSavedGames.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // create a Rectangle to logically represent the tanks
        time1 = new Rectangle();
        time1.x = 800f / 3 - 40; // center the bucket horizontally
        time1.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        time1.width = 300;
        time1.height = 300;

        time2 = new Rectangle();
        time2.x = 3* 800f / 3 - 580; // center the bucket horizontally
        time2.y = 80; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        time2.width = 300;
        time2.height = 300;

        SavedGames = new Rectangle();
        SavedGames.x = 3* 800f / 3 - 690; // center the bucket horizontally
        SavedGames.y = 180; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        SavedGames.width = 520;
        SavedGames.height = 420;

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
//        game.font.draw(game.batch, "Tank Star", 350, 400);
        game.batch.draw(time1Image, time1.x, time1.y, time1.width, time1.height);
        game.batch.draw(time2Image, time2.x, time2.y, time2.width, time2.height);
        game.batch.draw(SavedGamesTxt, SavedGames.x, SavedGames.y, SavedGames.width, SavedGames.height);
        game.batch.end();


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
        time1Image.dispose();
        time2Image.dispose();
        EnvironmentWar.dispose();
        SavedGamesTxt.dispose();
    }

}

