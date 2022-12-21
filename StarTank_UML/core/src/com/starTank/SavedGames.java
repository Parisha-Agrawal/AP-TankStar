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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavedGames implements Screen {

    private final Shoot game;
    public static int TotalGames = 5;
    public static int GamesTillNow = 0;
    private final Texture SavedGamesTxt;
    private final Music EnvironmentWar;
    private final Rectangle SavedGames;

    private final TextureRegion backgroundTexture;
    private final OrthographicCamera camera;
    private static int cannotLoadFlag = 0;
//    private HashMap<String,String> fNameTime; // for savedFileName and time

    public SavedGames(final Shoot game, GameInfo gameInfo) throws IOException {
        this.game = game;
        SavedGamesTxt = new Texture(Gdx.files.internal("SavedGamesTxt.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("bgSavedGames.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
//        this.fNameTime = game.getfNameTime();

        SavedGames = new Rectangle();
        SavedGames.x = 3* 800f / 3 - 690; // center the bucket horizontally
        SavedGames.y = 180; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        SavedGames.width = 520;
        SavedGames.height = 420;

        if (game.getfNameTime().size() == 0){
            game.addToFNameTime("saveGame1.txt","");
            game.addToFNameTime("saveGame2.txt","");
            game.addToFNameTime("saveGame3.txt","");
            game.addToFNameTime("saveGame4.txt","");
            game.addToFNameTime("saveGame5.txt","");
        }

        if (gameInfo!=null){
            saveGame(gameInfo);
        }

    }

    public void saveGame(GameInfo gameInfo) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(Files.newOutputStream(Paths.get("saveGame"+(GamesTillNow+1)+".txt")));
            out.writeObject(gameInfo);
            game.addToFNameTime("saveGame"+(GamesTillNow+1)+".txt",getCurrTime());
            GamesTillNow++;
        } finally {
            assert out != null;
            out.close();
        }
    }

    public void loadGame(int idx) throws IOException, ClassNotFoundException {
        if (idx<=GamesTillNow){
            ObjectInputStream in = null;
            try {
                in =  new ObjectInputStream (Files.newInputStream(Paths.get("saveGame"+(idx)+".txt")));
                GameInfo gameInfo = (GameInfo) in.readObject();
                game.setScreen(new GameScreen(game,gameInfo));
            } finally {
                in.close();
            }
        }
    }

    public static String getCurrTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return (formatter.format(date));
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
        game.batch.draw(SavedGamesTxt, SavedGames.x, SavedGames.y, SavedGames.width, SavedGames.height);

        int i = 0;
        int y_up = 300;
        while (i<GamesTillNow){
            game.font.draw(game.batch, (i+1)+") "+game.getfNameTime().get("saveGame"+(i+1)+".txt"), 300, y_up);
            i++;
            y_up -= 40;
        }

        game.font.draw(game.batch, "Press esc key to exit to HomePage", 50, 50);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new HomePage(game));
            dispose();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
            try {
                loadGame(1);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
            try {
                loadGame(2);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
            try {
                loadGame(3);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
            try {
                loadGame(4);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            dispose();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)){
            try {
                loadGame(5);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
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
//        time1Image.dispose();
//        time2Image.dispose();
        EnvironmentWar.dispose();
        SavedGamesTxt.dispose();
    }

}

