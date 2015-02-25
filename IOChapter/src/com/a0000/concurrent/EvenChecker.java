package com.a0000.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/2/19.
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        this.generator = g;
        this.id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val%2!=0) {
                System.out.println(val + " not even!");
                generator.cancel(); // Cancel all EvenCheckers.
            }
        }
    }

    // Test any type of IntGenerator;
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Ctrl-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<count; i++) {
            exec.execute(new EvenChecker(gp, i));
        }
        exec.shutdown();
    }

    // Default value for count
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
