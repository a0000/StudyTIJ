package com.a0000.concurrent;

/**
 * Created by Administrator on 2015/2/20.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public synchronized static int nextSerialNumber() {
        return serialNumber++; // Not thread-safe
    }
}
