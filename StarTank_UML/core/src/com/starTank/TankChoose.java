package com.starTank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Objects;

public class TankChoose implements Screen {
    private final Shoot game;
    private final String player;
    private final String chooseType;
    private final Texture AbramsTankImage;
    private final Texture TigerTankImage;
    private final Texture AtomicTankImage;
    private final Texture ChooseTankTxt;
    private final Music EnvironmentWar;
    private final Rectangle ChooseTank;
    private final Rectangle AbramsTank;
    private final Rectangle TigerTank;
    private final Rectangle AtomicTank;
    private Player p1;
    private Player p2;
    private Tank p1Tank;
    private Tank p2Tank;
    private final TextureRegion backgroundTexture;
    private final OrthographicCamera camera;

    public TankChoose(final Shoot game, String player, String chooseType, Player p1) {
        this.game = game;
        this.player = player;
        this.chooseType = chooseType;
        if (Objects.equals(player, "Player 1")){

        }
        if (Objects.equals(player, "Player 2")){

            p2 = new Player("p2",10,null);
        }
        this.p1 = p1;

        AbramsTankImage = new Texture(Gdx.files.internal("AbramsTankImage.png"));
        TigerTankImage = new Texture(Gdx.files.internal("TigerTankImage.png"));
        AtomicTankImage = new Texture(Gdx.files.internal("AtomicTankImage.png"));
        Texture backgroundImage = new Texture(Gdx.files.internal("Background3.png"));
        ChooseTankTxt=new Texture(Gdx.files.internal("ChooseTankTxt.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 555, 260);

        EnvironmentWar = Gdx.audio.newMusic(Gdx.files.internal("tankwar.wav"));
        EnvironmentWar.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        // create a Rectangle to logically represent the tanks
        AbramsTank = new Rectangle();
        AbramsTank.x = 800f / 3 - 240;
        AbramsTank.y = 98;
        AbramsTank.width = 240;
        AbramsTank.height = 240;

        AtomicTank = new Rectangle();
        AtomicTank.x = 2* 800f / 3 - 240;
        AtomicTank.y = 168;
        AtomicTank.width = 200;
        AtomicTank.height = 100;

        TigerTank = new Rectangle();
        TigerTank.x = 3* 800f / 3 - 240;
        TigerTank.y = 162;
        TigerTank.width = 200;
        TigerTank.height = 100;

        ChooseTank=new Rectangle();
        ChooseTank.x= 3* 800f / 3 - 790;
        ChooseTank.y = 280;
        ChooseTank.width = 320;
        ChooseTank.height = 260;

    }

    public void setPlayer1Tank(Tank player1Tank){
//      set the provided tank as player 1 tank
        p1.getInfo().setTank(player1Tank);
    }
    public void setPlayer2Tank(Tank player2Tank){
//      set the provided tank as player 2 tank
        p2.getInfo().setTank(player2Tank);
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
        game.font.draw(game.batch, this.player, 50, 400);
        game.font.draw(game.batch, this.chooseType, 50, 380);
        game.font.draw(game.batch, "Press esc key to exit to HomePage", 50, 50);
        game.batch.draw(AbramsTankImage, AbramsTank.x, AbramsTank.y, AbramsTank.width, AbramsTank.height);
        game.batch.draw(AtomicTankImage, AtomicTank.x, AtomicTank.y, AtomicTank.width, AtomicTank.height);
        game.batch.draw(TigerTankImage, TigerTank.x, TigerTank.y, TigerTank.width, TigerTank.height);
        game.batch.draw(ChooseTankTxt, ChooseTank.x, ChooseTank.y, ChooseTank.width, ChooseTank.height);
        game.batch.end();
        if (Objects.equals(player, "Player 1")){
            p1 = new Player(player,10,null);
        }
        if (Objects.equals(player, "Player 2")){
            p2 = new Player(player,10,null);
        }
        if ((Objects.equals(player, "Player 1")) && (Gdx.input.isKeyPressed(Input.Keys.A))){
            p1Tank = new Tank("AbramsTankImage.png",10,800/4 - 200,82,45,"cannon",1);
            setPlayer1Tank(p1Tank);
            game.setScreen(new TankChoose(game,"Player 2","Press keys 1, 2 or 3 to select the tanks",p1));
            dispose();
        }
        if ((Objects.equals(player, "Player 1")) && (Gdx.input.isKeyPressed(Input.Keys.B))){
            p1Tank = new Tank("AtomicTankImage.png",10,800/4 - 200,82,45,"cannon",1);
            setPlayer1Tank(p1Tank);
            game.setScreen(new TankChoose(game,"Player 2","Press keys 1, 2 or 3 to select the tanks",p1));
            dispose();
        }
        if ((Objects.equals(player, "Player 1")) && (Gdx.input.isKeyPressed(Input.Keys.C))){
            p1Tank = new Tank("TigerTankImage.png",10,800/4 - 200,82,45,"cannon",1);
            setPlayer1Tank(p1Tank);
            game.setScreen(new TankChoose(game,"Player 2","Press keys 1, 2 or 3 to select the tanks",p1));
            dispose();
        }
        if ((Objects.equals(player, "Player 2")) && (Gdx.input.isKeyPressed(Input.Keys.NUM_1))){
            p2Tank = new Tank("AbramsFlipImage.png",10,2* 800/3 +5000,100,45,"cannon",1);
            setPlayer2Tank(p2Tank);
//            System.out.println("\n\n"+p1.getName()+" "+p1.getInfo().getHealth()+" "+p1.getInfo().getTank().getName()+"\n\n");
//            System.out.println("\n\n"+p2.getName()+" "+p2.getInfo().getHealth()+" "+p2.getInfo().getTank().getName()+"\n\n");
            game.setScreen(new GameScreen(game,p1,p2));
            dispose();
        }
        if ((Objects.equals(player, "Player 2")) && (Gdx.input.isKeyPressed(Input.Keys.NUM_2))){
            p2Tank = new Tank("AtomicFlipTankImage.png",10,2* 800/3 +5000,100,45,"cannon",1);
            setPlayer2Tank(p2Tank);
            game.setScreen(new GameScreen(game,p1,p2));
            dispose();
        }
        if ((Objects.equals(player, "Player 2")) && (Gdx.input.isKeyPressed(Input.Keys.NUM_3))){
            p2Tank = new Tank("TigerBowUpFlipGameImage.png",10,2* 800/3 +5000,100,45,"cannon",1);
            setPlayer2Tank(p2Tank);
            game.setScreen(new GameScreen(game,p1,p2));
            dispose();
        }
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
        AbramsTankImage.dispose();
        AtomicTankImage.dispose();
        TigerTankImage.dispose();
        EnvironmentWar.dispose();
        ChooseTankTxt.dispose();
    }

}

