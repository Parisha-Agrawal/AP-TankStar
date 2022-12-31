package com.starTank;

import java.io.Serializable;

public class GameInfo implements Serializable {
    private final PlayerGameInfo P1GameInfo;
    private final PlayerGameInfo P2GameInfo;

    public GameInfo(PlayerGameInfo p1GameInfo, PlayerGameInfo p2GameInfo) {
        P1GameInfo = p1GameInfo;
        P2GameInfo = p2GameInfo;
    }

    public PlayerGameInfo getP1GameInfo() {
        return P1GameInfo;
    }

    public PlayerGameInfo getP2GameInfo() {
        return P2GameInfo;
    }
}
