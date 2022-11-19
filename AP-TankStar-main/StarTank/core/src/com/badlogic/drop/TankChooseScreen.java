package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
public class TankChooseScreen implements Screen {


    final Drop game;
    Texture AbramsTankImage;
    Texture TigerTankImage;
    Texture AtomicTankImage;
    Texture ChooseTankTxt;
    Music EnvironmentWar;
    Rectangle AbramsTank;
    Rectangle ChooseTank;
    Rectangle TigerTank;
    Rectangle AtomicTank;

    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public TankChooseScreen(final Drop game) {
        this.game = game;
        AbramsTankImage = new Texture(Gdx.files.internal("AbramsTankImage.png"));
        TigerTankImage = new Texture(Gdx.files.internal("TigerTankImage.png"));
        AtomicTankImage = new Texture(Gdx.files.internal("AtomicTankImage.png"));
        backgroundImage = new Texture(Gdx.files.internal("ChooseTankBackground.jpg"));
        ChooseTankTxt=new Texture(Gdx.files.internal("ChooseTankTxt.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // create a Rectangle to logically represent the tanks
        AbramsTank = new Rectangle();
        AbramsTank.x = 800 / 3 - 240; // center the bucket horizontally
        AbramsTank.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        AbramsTank.width = 200;
        AbramsTank.height = 200;

        AtomicTank = new Rectangle();
        AtomicTank.x = 2* 800 / 3 - 240; // center the bucket horizontally
        AtomicTank.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        AtomicTank.width = 200;
        AtomicTank.height = 200;

        TigerTank = new Rectangle();
        TigerTank.x = 3* 800 / 3 - 240; // center the bucket horizontally
        TigerTank.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        TigerTank.width = 200;
        TigerTank.height = 200;

        ChooseTank=new Rectangle();
        ChooseTank.x= 3* 800 / 3 - 790;
        ChooseTank.y = 328; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        ChooseTank.width = 200;
        ChooseTank.height = 200;

    }

    @Override
    public void show() {
        EnvironmentWar.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Player 1", 80, 400);
        game.batch.draw(AbramsTankImage, AbramsTank.x, AbramsTank.y, AbramsTank.width, AbramsTank.height);
        game.batch.draw(AtomicTankImage, AtomicTank.x, AtomicTank.y, AtomicTank.width, AtomicTank.height);
        game.batch.draw(TigerTankImage, TigerTank.x, TigerTank.y, TigerTank.width, TigerTank.height);
        game.batch.draw(ChooseTankTxt, ChooseTank.x, ChooseTank.y, ChooseTank.width, ChooseTank.height);
        game.batch.end();

//        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
//            game.setScreen(new GameScreen(game));
//            dispose();
//        }

//        if (Gdx.input.isTouched()) {
//            game.setScreen(new HomePage(game));
//            dispose();
//        }
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
        AbramsTankImage.dispose();
        AtomicTankImage.dispose();
        TigerTankImage.dispose();
        EnvironmentWar.dispose();
        ChooseTankTxt.dispose();
    }

}
