package com.a0000.concurrent.part2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.a0000.io.utils.Print.*;

// Performs some portion of a task
class TaskPortion implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        print(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

// Waits on the CountDownLatch;
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            print("Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}
/**
 * Created by Administrator on 2015/5/17.
 */
public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // All must share a single CountDownLatch object;
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i=0; i<10; i++) {
            exec.execute(new WaitingTask(latch));
        }
        for (int i=0; i<SIZE; i++) {
            exec.execute(new TaskPortion(latch));
        }
        print("Launched all tasks");
        exec.shutdown(); // Quit when all tasks complete
    }
}
