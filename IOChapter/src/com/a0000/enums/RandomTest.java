package com.a0000.enums;

enum Activity {
    SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}
/**
 * Created by 100 on 2015/2/5.
 */
public class RandomTest {
    public static void main(String[] args) {
        for (int i=0; i<20; i++) {
            System.out.print(Enums.random(Activity.class) + " ");
        }
    }
}
