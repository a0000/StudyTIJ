package com.a0000.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2015/2/13.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
