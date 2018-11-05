package me.changjie.entity;

/**
 * Created by ChangJie on 2018/11/5.
 */
public class Robot {

    //需要注入 hand 和 mouth
    private Hand hand;
    private Mouth mouth;

    public void show(){
        hand.waveHand();
        mouth.speak();
    }

}
