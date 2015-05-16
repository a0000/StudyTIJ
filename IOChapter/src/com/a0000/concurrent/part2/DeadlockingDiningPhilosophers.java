package com.a0000.concurrent.part2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/5/16.
 * Demonstrates how deadlock can be hidden in a program.
 * {Args: 0 5 timeout}
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length>0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length>1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i=0; i<size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i=0; i<size; i++) {
            exec.execute(new Philosophers(sticks[i], sticks[(i+1) % size], i, ponder));
        }
        if (args.length==3 && "timeout".equals(args[2])) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();

    }
}
