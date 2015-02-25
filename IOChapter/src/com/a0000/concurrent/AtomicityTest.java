package com.a0000.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/2/20.
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    public synchronized void evenIncrement() {
        i++;
        Thread.yield();
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);

        TimeUnit.SECONDS.sleep(1);

        while (true) {
            int val = at.getValue();
            if (val%2!=0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
