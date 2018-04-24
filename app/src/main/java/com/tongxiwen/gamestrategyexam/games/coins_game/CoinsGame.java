package com.tongxiwen.gamestrategyexam.games.coins_game;

/**
 * Created by tong.xiwen on 2018/4/20.
 * 两个人各出一次硬币
 * 若为两个正面，则B给A 3元
 * 若为两个反面，则B给A 1元
 * 否则A给B两元
 *
 * true 为正面，false为反面
 */
public class CoinsGame {
    private int cashA;
    private int cashB;

    public CoinsGame(int defaultCash){
        initGame(defaultCash);
    }

    /**
     * 初始化游戏
     * @param defaultCash 每人最初金额
     */
    private void initGame(int defaultCash) {
        cashA = defaultCash;
        cashB = defaultCash;
    }

    /**
     * 玩一轮
     * @param coinA A出示硬币
     * @param coinB B出示硬币
     */
    public void play(boolean coinA, boolean coinB){
        if (coinA && coinB){
            cashB -= 3;
            cashA += 3;
            return;
        }
        if (!coinA && !coinB){
            cashB -= 1;
            cashA += 1;
            return;
        }
        cashB += 2;
        cashA -= 2;
    }

    /**
     * 获取当前筹码状态
     * @return 0为A，1为B
     */
    public int[] getCashStatus(){
        return new int[]{cashA, cashB};
    }

    /**
     * 重启游戏
     * @param defaultCash 初始金额
     */
    public void restart(int defaultCash){
        initGame(defaultCash);
    }
}
