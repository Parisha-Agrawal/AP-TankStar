package com.starTank;

import java.io.Serializable;

public class Weapon implements Serializable {
    private String name;
    private float exactDamage;

    public Weapon(String name, float exactDamage) {
        this.name = name;
        this.exactDamage = exactDamage;
    }
}
