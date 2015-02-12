package com.a0000.concurrent;

import net.mindview.util.ThreeTuple;

import java.util.concurrent.TimeUnit;

import static com.a0000.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/12.
 * Daemon threads don't prevent the program from ending.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
        print("All daemons started2");

    }
}
