package com.a0000.concurrent;

import java.util.concurrent.TimeUnit;

import static com.a0000.io.utils.Print.*;
class DualSynch {
    private Object syncObject = new Object();
    public synchronized void f() {
        for (int i=0; i<5; i++) {
            print("f()");
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void g() {
        synchronized (syncObject) {
            for (int i=0; i<5; i++) {
                print("g()");
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * Created by Administrator on 2015/2/21.
 * Synchronizing on another object.
 */
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
