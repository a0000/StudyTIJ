package com.a0000.concurrent;

/**
 * Created by 100 on 2015/2/12.
 * The most basic use of the Thread class.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
