package com.a0000.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Synchronize the entire method;
class ExplicitPairManager1 extends PairManager {

    private Lock lock = new ReentrantLock();
    @Override
    public void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Pair getPair() {
        lock.lock();
        Pair pair = new Pair(p.getX(), p.getY());
        lock.unlock();
        return pair;
    }
}

// Using a critical section;
class ExplicitPairManager2 extends PairManager {

    private Lock lock = new ReentrantLock();
    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }

    @Override
    public Pair getPair() {
        lock.lock();
        Pair pair = new Pair(p.getX(), p.getY());
        lock.unlock();
        return pair;
    }
}

/**
 * Created by Administrator on 2015/2/21.
 * Using explicit Lock objects to create critical sections.
 */
public class ExplicitCriticalSection {
    public static void main(String[] args) {
        PairManager
                pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pman1, pman2);
    }
}
