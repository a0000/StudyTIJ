package com.a0000.concurrent;

import java.util.concurrent.TimeUnit;

import static com.a0000.io.utils.Print.*;

class ADemon implements Runnable {

    @Override
    public void run() {
        try {
            print("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException");
        } finally {
            print("This should always run?");
        }
    }
}
/**
 * Created by Administrator on 2015/2/13.
 * Daemon threads don't run the finally clause
 */
public class DaemonsDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.MILLISECONDS.sleep(170);
    }
}
