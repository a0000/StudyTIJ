package com.a0000.concurrent;

import net.mindview.util.ThreeTuple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/2/12.
 * Shows the use of thread priorities.
 */
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // An expensive , interruputable operation;
            for (int i=0; i<100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i%1000==0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if(--countDown==0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
