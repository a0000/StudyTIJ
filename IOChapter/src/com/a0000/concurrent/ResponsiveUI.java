package com.a0000.concurrent;

import java.io.IOException;

class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while (d > 0) {
            d = d + (Math.PI + Math.E)/d;
        }
        System.in.read(); // Never gets here
    }
}

/**
 * Created by Administrator on 2015/2/15.
 * User interface responsiveness.
 * {RunByHand}
 */
public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
        // !new UnresponsiveUI(); // Must kill this process
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}
