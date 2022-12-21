package com.starTank;

import java.io.Serializable;

public class PlayerGameInfo implements Serializable {
    private float health;
    private Tank tank;

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public PlayerGameInfo(float health, Tank tank) {
        this.health = health;
        this.tank = tank;
    }
}
