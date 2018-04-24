package com.tongxiwen.gamestrategyexam.games.nim_game;

import java.util.Random;

/**
 * Created by tong.xiwen on 2018/4/23.
 */
public class NimGame {
    private int mTotal;
    private int mHandSize;
    private boolean isA;

    public NimGame(){
        initGame();
    }

    private void initGame() {
        mTotal = new Random().nextInt(200);
        mHandSize = new Random().nextInt(7) + 3;
    }

    /**
     * 新游戏
     */
    public void restart(){
        initGame();
    }

    /**
     * 获取一次可取数量
     */
    public int getHandSize() {
        return mHandSize;
    }

    /**
     * 获取总值
     */
    public int getTotal() {
        return mTotal;
    }

    /**
     * 获取
     * @param n 这次拿去的数字
     * @return  拿去后的剩余
     */
    public int take(int n){
        isA = !isA;
        mTotal -= n;
        return mTotal;
    }

    /**
     * 当前玩家
     * @return 若为先手则返回true
     */
    public boolean getIfATurn(){
        return isA;
    }
}
