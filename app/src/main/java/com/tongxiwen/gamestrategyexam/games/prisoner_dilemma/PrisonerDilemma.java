package com.tongxiwen.gamestrategyexam.games.prisoner_dilemma;

/**
 * Created by tong.xiwen on 2018/4/20.
 * 两个囚犯，初始金额均为80
 * 分别对两个囚犯进行询问
 * 若均坦白，则均罚款4，若均隐瞒，则jun获得2
 * 若一方隐瞒一方坦白，则坦白者获得8，隐瞒者罚款8
 *
 * true 坦白， false 隐瞒
 */
public class PrisonerDilemma {
    private int cashA;
    private int cashB;

    public PrisonerDilemma(int defaultCash){
        initGame(defaultCash);
    }

    private void initGame(int defaultCash) {
        cashA = 80;
        cashB = 80;
    }

    public void restart(int defaultCash){
        initGame(defaultCash);
    }

    public int[] getCashStatus(){
        return new int[]{cashA, cashB};
    }

    public void play(boolean pA, boolean pB){
        if (pA){
            if (pB){
                cashA-=4;
                cashB-=4;
            }else {
                cashA+=8;
                cashB-=8;
            }
        }else {
            if (pB){
                cashA-=8;
                cashB+=8;
            }else {
                cashA+=2;
                cashB+=2;
            }
        }
    }
}
