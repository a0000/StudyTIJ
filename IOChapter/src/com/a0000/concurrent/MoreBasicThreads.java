package com.a0000.concurrent;

/**
 * Created by 100 on 2015/2/12.
 * Adding more threads.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
