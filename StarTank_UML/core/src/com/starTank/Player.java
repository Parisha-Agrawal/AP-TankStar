package com.starTank;

public class Player {
    private String name;
    private float health;
    private PlayerGameInfo info = new PlayerGameInfo();

    public PlayerGameInfo getInfo() {
        return info;
    }

    public void setInfo(PlayerGameInfo info) {
        this.info = info;
    }

    public Player() {
        this.health = 20;
    }

    public void shoot(int power){
//        player will shoot from its location and tank angle with given power
    }

    public void pauseGame(){
//        here player can pause the game
    }

    public void goForward(){
//        here player can move the tank in forward direction
    }

    public void goBackward(){
//        here player can move the tank in backward direction
    }

    public void angleUp(){
//        here player move the angle to shoot upwards
    }
    public void angleDown(){
//        here player move the angle to shoot downwards
    }
}
