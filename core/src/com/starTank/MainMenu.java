package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MainMenu implements Screen {
    private final Shoot game;
    private final TextureRegion backgroundTexture;
    private final OrthographicCamera camera;

    public MainMenu(final Shoot game) {
        this.game = game;
        Texture backgroundImage = new Texture(Gdx.files.internal("ChooseTankBackground.jpg"));
//        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1376, 771);
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 480);
        game.font.draw(game.batch, "Welcome to TankStar!", 300, 240);
        game.font.draw(game.batch, "Click to begin!", 330, 140);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= 280 && touchPos.x <=  450 && touchPos.y >= 120 && touchPos.y <=  140){
                game.setScreen(new HomePage(game));
                dispose();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            game.setScreen(new HomePage(game));
        dispose();

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

    }

}