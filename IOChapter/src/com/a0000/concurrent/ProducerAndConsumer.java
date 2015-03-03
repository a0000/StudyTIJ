package com.a0000.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 商品
 */
class Product {
    private final int id;
    private static int identId = 1;

    public Product() {
        synchronized (Product.class) {
            this.id = identId++;
        }
    }

    @Override
    public String toString() {
        return "商品ID： " + id;
    }
}

/**
 * 仓库
 */
class Warehouse {

    private int maxSize = 100;
    private List<Product> products = new ArrayList<Product>();
    public synchronized void produce(Product product, Producer producer) {
        if (products.size()>=maxSize) {
            System.out.println("仓库已满");
            while (products.size()>=maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            products.add(product);
            System.out.println(producer + " 生产了 " + product + "\t仓库已有" + products.size() + "个商品");
        }
        notifyAll();
    }

    public synchronized void consume(Consumer c) {
        if (products.size()>0) {
            Product product = products.remove(products.size() - 1);
            System.out.println(c + " 消费了 " + product + "\t仓库还有" + products.size() + "个商品");
        } else {
            System.out.println("没有商品了");
            while (products.size()==0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        notifyAll();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {

    private final int id;
    private Warehouse warehouse;
    private Random rand = new Random(47);

    public Producer(int id, Warehouse warehouse) {
        this.id = id;
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "生产者" + id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse.produce(new Product(), this);
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private final int id;
    private Warehouse warehouse;
    private Random rand = new Random(47);

    public Consumer(int id, Warehouse warehouse) {
        this.id = id;
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "消费者" + id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse.consume(this);
        }
    }
}

/**
 * Created by 100 on 2015/3/2.
 * 自己写的生产者与消费者
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        int consumerCount = 3;
        int producerCount = 5;
        Warehouse warehouse = new Warehouse();
        for (int i=0; i<producerCount; i++) {
            new Thread(new Producer(i,warehouse)).start();
        }
        for (int i=0; i<consumerCount; i++) {
            new Thread(new Consumer(i,warehouse)).start();
        }
    }

}
