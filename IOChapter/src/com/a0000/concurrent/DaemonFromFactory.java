package com.a0000.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static com.a0000.io.utils.Print.*;

/**
 * Created by Administrator on 2015/2/13.
 * Using a Thread Factory to create daemons.
 */
public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("Interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i=0; i<10; i++) {
            exec.execute(new DaemonFromFactory());
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
}
