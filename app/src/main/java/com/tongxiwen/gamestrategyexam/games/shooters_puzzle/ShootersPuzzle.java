package com.tongxiwen.gamestrategyexam.games.shooters_puzzle;

import java.util.Random;

/**
 * Created by tong.xiwen on 2018/4/23.
 */
public class ShootersPuzzle {

    private boolean[] shooterStatus;

    public static final int A = 8;
    public static final int B = 6;
    public static final int C = 4;
    public static final int DEAD = 0;

    public ShootersPuzzle() {
        restart();
    }

    public void restart() {
        shooterStatus = new boolean[]{true, true, true};
    }

    public boolean[] getShooterStatus(){
        return shooterStatus;
    }

    public boolean[] fire(int targetA, int targetB, int targetC) {
        if (shooterStatus[0])
            shooterStatus[0] = (new Random().nextInt(10) + 1) < targetA;
        if (shooterStatus[1])
            shooterStatus[1] = (new Random().nextInt(10) + 1) < targetB;
        if (shooterStatus[2])
            shooterStatus[2] = (new Random().nextInt(10) + 1) < targetC;
        return shooterStatus;
    }
}
