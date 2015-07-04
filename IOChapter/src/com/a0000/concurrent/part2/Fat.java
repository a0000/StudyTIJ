package com.a0000.concurrent.part2;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Administrator on 2015/6/10.
 * Objects that are expensive to create.
 */
public class Fat {
    private volatile double d; // Prevent optimization
    private static int counter = 0;
    private final int id = counter++;
    public Fat() {
        // Expensive, interruptible operation;
        for (int i=0; i<10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
