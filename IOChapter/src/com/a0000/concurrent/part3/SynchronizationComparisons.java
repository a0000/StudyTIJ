package com.a0000.concurrent.part3;
// Comparing the performance of explicit Locks
// and Atomics versus the synchronized keyword.

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.a0000.io.utils.Print.*;

abstract class Accumulator {
    public static long cycles = 50000l;
    // Number of Modiffiers and Readers during each test;
    private static final int N = 4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N*2);
    private static CyclicBarrier barrier = new CyclicBarrier(N*2+1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];
    static {
        // Load the array of random numbers;
        Random rand = new Random(47);
        for (int i=0; i<SIZE; i++) {
            preLoaded[i] = rand.nextInt();
        }
    }
    public abstract void accumulate();
    public abstract long read();
    private class Modifier implements Runnable {
        @Override
        public void run() {
            for (long i=0; i<cycles; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class Reader implements Runnable {
        private volatile long value;

        @Override
        public void run() {
            for (long i=0; i<cycles; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void timedTest() {
        long start = System.nanoTime();
        for (int i=0; i<N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        printf("%-13s: %13d\n", id, duration);
    }
    public static void report(Accumulator acc1, Accumulator acc2) {
        printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id, (double)acc1.duration/(double)acc2.duration);
    }
}

class BaseLine extends Accumulator {
    @Override
    public void accumulate() {
        value += preLoaded[index++];
        if (index>=SIZE) {
            index = 0;
        }
    }

    @Override
    public long read() {
        return value;
    }
}

class SynchronizedTest extends Accumulator {
    {id = "sychnorized";}

    @Override
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index>=SIZE) {
            index = 0;
        }
    }

    @Override
    public long read() {
        return value;
    }
}

class LockTest extends Accumulator {
    {id = "Lock";}
    private Lock lock = new ReentrantLock();

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index>=SIZE) {
                index = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

/**
 * Created by Administrator on 2015/7/21.
 */
public class SynchronizationComparisons {
}
