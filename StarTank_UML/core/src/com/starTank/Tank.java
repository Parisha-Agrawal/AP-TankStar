package com.starTank;

import java.io.Serializable;

public class Tank implements Serializable {
    private String name;
    private float fuel;
    private int positionX;
    private int positionY;
    private int angle;
    private Weapon weapon;

    public Tank(String name, float fuel, int positionX, int positionY, int angle, String weaponName, float weaponDamage) {
        this.name = name;
        this.fuel = fuel;
        this.positionX = positionX;
        this.positionY = positionY;
        this.angle = angle;
        this.weapon = new Weapon(weaponName,weaponDamage);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getName() {
        return name;
    }

    public void moveForward(){
//        here tank move in forward direction
    }

    public void moveBackward(){
//        here tank move in backward direction
    }

    public void angleUp(){
//        here tank moves the shooting angle upwards
    }
    public void angleDown(){
//        here tank moves the shooting angle downwards
    }
    public void changeWeapon(Weapon w){
//        here tank changes its weapon to the provided weapon
    }
}
