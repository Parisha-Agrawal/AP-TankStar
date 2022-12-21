package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class WinScreen implements Screen {
    private final Shoot game;
    private final OrthographicCamera camera;
    private final String winP;
    public WinScreen(final Shoot game,String winP) {
        this.game = game;
        this.winP = winP;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0, 0.2f, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, winP+" wins!!" , 300, 300);
        game.font.draw(game.batch, "Press esc key to exit to HomePage", 50, 50);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
    }
}
