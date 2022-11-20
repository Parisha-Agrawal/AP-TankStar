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
public class TankChoosePlayer2 implements Screen {


    final Drop game;
    Texture AbramsFlipTankImage;
    Texture Tiger_HDFlipImage;
    Texture AtomicFlipImage;
    Texture ChooseTankTxt;
    Music EnvironmentWar;
    Rectangle AbramsFlipTank;
    Rectangle ChooseTank;
    Rectangle Tiger_HDFlip;
    Rectangle AtomicFlipTank;

    private Texture backgroundImage;
    private TextureRegion backgroundTexture;
    OrthographicCamera camera;

    public TankChoosePlayer2(final Drop game) {
        this.game = game;
        AbramsFlipTankImage = new Texture(Gdx.files.internal("AbramsFlipImage.png"));
        Tiger_HDFlipImage = new Texture(Gdx.files.internal("Tiger_HDFlipImage.png"));
        AtomicFlipImage = new Texture(Gdx.files.internal("AtomicFlipTankImage.png"));
        backgroundImage = new Texture(Gdx.files.internal("Background3.png"));
        ChooseTankTxt=new Texture(Gdx.files.internal("ChooseTankTxt.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // create a Rectangle to logically represent the tanks
        AbramsFlipTank = new Rectangle();
        AbramsFlipTank.x = 800 / 3 - 240; // center the bucket horizontally
        AbramsFlipTank.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        AbramsFlipTank.width = 200;
        AbramsFlipTank.height = 150;

        AtomicFlipTank = new Rectangle();
        AtomicFlipTank.x = 2* 800 / 3 - 240; // center the bucket horizontally
        AtomicFlipTank.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        AtomicFlipTank.width = 200;
        AtomicFlipTank.height = 150;

        Tiger_HDFlip = new Rectangle();
        Tiger_HDFlip.x = 3* 800 / 3 - 240; // center the bucket horizontally
        Tiger_HDFlip.y = 128; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        Tiger_HDFlip.width = 200;
        Tiger_HDFlip.height = 130;

        ChooseTank=new Rectangle();
        ChooseTank.x= 3* 800 / 3 - 790;
        ChooseTank.y = 328; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        ChooseTank.width = 200;
        ChooseTank.height = 200;

    }




    @Override
    public void show() { EnvironmentWar.play(); }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Player 2", 80, 400);
        game.batch.draw(AbramsFlipTankImage, AbramsFlipTank.x, AbramsFlipTank.y, AbramsFlipTank.width, AbramsFlipTank.height);
        game.batch.draw(AtomicFlipImage, AtomicFlipTank.x, AtomicFlipTank.y, AtomicFlipTank.width, AtomicFlipTank.height);
        game.batch.draw(Tiger_HDFlipImage, Tiger_HDFlip.x, Tiger_HDFlip.y, Tiger_HDFlip.width, Tiger_HDFlip.height);
        game.batch.draw(ChooseTankTxt, ChooseTank.x, ChooseTank.y, ChooseTank.width, ChooseTank.height);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new GameScreen(game));
            dispose();
        }

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
        AbramsFlipTankImage.dispose();
        AtomicFlipImage.dispose();
        Tiger_HDFlipImage.dispose();
        EnvironmentWar.dispose();
        ChooseTankTxt.dispose();
    }

}
