package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class HomePage implements Screen {
    private final Shoot game;
    private final Texture NewGameImage;
    private final Texture ResumeImage;
    private final Texture ExitImage;
    private final Texture TankStarImage;
    private final Music EnvironmentWar;
    private final Rectangle NewGame;
    private final Rectangle Resume;
    private final Rectangle Exit;
    private final Rectangle TankStar;

    private final TextureRegion backgroundTexture;
    private final OrthographicCamera camera;

    public HomePage(final Shoot game) {
        this.game = game;
        NewGameImage = new Texture(Gdx.files.internal("NewGameImage.png"));
        ResumeImage = new Texture(Gdx.files.internal("ResumeImage.png"));
        ExitImage = new Texture(Gdx.files.internal("ExitImage.png"));
        TankStarImage = new Texture(Gdx.files.internal("TankStarImage.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("ChooseTankBackground.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        NewGame = new Rectangle();
        NewGame.x = 800f / 3 - 40;
        NewGame.y = 128;
        NewGame.width = 280;
        NewGame.height = 300;

        Resume = new Rectangle();
        Resume.x = 3* 800f / 3 - 580;
        Resume.y = 80;
        Resume.width = 300;
        Resume.height = 300;

        Exit = new Rectangle();
        Exit.x = 2* 800f / 3 - 310;
        Exit.y = 28;
        Exit.width = 310;
        Exit.height = 300;

        TankStar = new Rectangle();
        TankStar.x = 3* 800f / 3 - 690;
        TankStar.y = 180;
        TankStar.width = 520;
        TankStar.height = 420;

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
        game.batch.draw(NewGameImage, NewGame.x, NewGame.y, NewGame.width, NewGame.height);
        game.batch.draw(ResumeImage, Resume.x, Resume.y, Resume.width, Resume.height);
        game.batch.draw(ExitImage, Exit.x, Exit.y, Exit.width, Exit.height);
        game.batch.draw(TankStarImage, TankStar.x, TankStar.y, TankStar.width, TankStar.height);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= 800f / 3 - 40 && touchPos.x <=  800f / 3 - 40 + 280 && touchPos.y >= 260 && touchPos.y <=  320){
                game.setScreen(new TankChoose(game,"Player 1","Press keys A, B or C to select the tanks",null));
                dispose();
            }
            else if (touchPos.x >= 3* 800f / 3 - 580 && touchPos.x <=  3* 800f / 3 - 580 + 300 && touchPos.y >= 200 && touchPos.y <=  250){
                try {
                    game.setScreen(new SavedGames(game,null));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dispose();
            }
            else if (touchPos.x >= 2* 800f / 3 - 310 && touchPos.x <=  2* 800f / 3 - 310 + 310 && touchPos.y >= 150 && touchPos.y <=  195){
                exit();
                dispose();
            }
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

    public void exit(){
        Gdx.app.exit();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        NewGameImage.dispose();
        ExitImage.dispose();
        ResumeImage.dispose();
        EnvironmentWar.dispose();
        TankStarImage.dispose();
    }
}

