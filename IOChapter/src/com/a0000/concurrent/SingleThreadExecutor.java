package com.a0000.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 100 on 2015/2/12.
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i=0; i<5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
