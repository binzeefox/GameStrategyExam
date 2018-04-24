package com.tongxiwen.gamestrategyexam.games.tourist_puzzle;

import java.util.Random;

/**
 * Created by tong.xiwen on 2018/4/23.
 * 一个人毁坏了两个旅行者价值位于70到100之间的瓶子
 * 他打算赔偿，但是不知道确切价格
 * 于是决定，让两个人分别写出瓶子的价格
 * 如果两者一样，则按价格赔偿
 * 如果不一样，则按照低价赔偿，并奖励低价者2，惩罚高价者2
 * 两人初始有10元
 */
public class TouristPuzzle {
    private int mPrice;
    private int cashA;
    private int cashB;

    public TouristPuzzle(){
        initGame();
    }

    private void initGame() {
        mPrice = new Random().nextInt(30) + 70;
        cashA = 10;
        cashB = 10;
    }

    public void restart(){
        initGame();
    }

    public int getPrice(){
        return mPrice;
    }

    public int[] getCashStatus(){
        return new int[]{cashA, cashB};
    }

    public int[] sign(int A, int B){
        if (A == B){
            cashA += A;
            cashB += B;
        }
        if (A > B){
            cashA -= 2;
            cashB += (B + 2);
        }
        if (B > A){
            cashA += (A + 2);
            cashB -= 2;
        }
        return getCashStatus();
    }

}
