package com.a0000.concurrent.part2;

/**
 * Created by A on 2015/5/16.
 * Chopstick for dining philosophers.
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
