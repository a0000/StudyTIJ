package com.a0000.concurrent.part2;

import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

import java.util.List;
import java.util.concurrent.*;

class ExchangerProducer<T> implements Runnable {

    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    public ExchangerProducer(Exchanger<List<T>> exchg, Generator<T> gen, List<T> holder) {
        exchanger = exchg;
        generator = gen;
        this.holder = holder;
    }
    @Override
    public void run() {
        int time = 0;
        try {
            while (!Thread.interrupted()) {
                time++;
                for (int i=0; i<ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                // Exchange full for empty;
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
            System.out.println("ExchangerProducer exchange times: " + time);
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    public ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
        exchanger = ex;
        this.holder = holder;
    }

    @Override
    public void run() {
        int time = 0;
        try {
            while (!Thread.interrupted()) {
                time++;
                holder = exchanger.exchange(holder);
                for (T t : holder) {
                    value = t; // Fetch out value
                    holder.remove(t); // OK for CopyOnWriteArrayList
                }
            }
        } catch (InterruptedException e) {
            // OK to terminate this way;
            System.out.println("ExchangerConsumer exchange times: "+time);
        }
        System.out.println("Final value: " + value);
    }
}

/**
 * Created by Administrator on 2015/6/10.
 */
public class ExchangerDemo {
    static int size = 10;
    static  int delay = 5; // Seconds

    public static void main(String[] args) throws InterruptedException {
        if (args.length>0) {
            size = new Integer(args[0]);
        }
        if (args.length>1) {
            delay = new Integer(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
        List<Fat>
                producerList = new CopyOnWriteArrayList<Fat>(),
                consumerList = new CopyOnWriteArrayList<Fat>();
        exec.execute(new ExchangerProducer<Fat>(xc, BasicGenerator.create(Fat.class), producerList));
        exec.execute(new ExchangerConsumer<>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        System.out.println(producerList.size()+"--"+consumerList.size());
        exec.shutdownNow();
    }
}
