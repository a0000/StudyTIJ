package com.a0000.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品
 */
class Product {
    private final int id;

    public Product(int id) {
        this.id = id;
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
    public synchronized void produce(Product product, Produce produce) {
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
            System.out.println(produce + " 生产了 " + product + "\t仓库已有" + products.size() + "个商品");
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
class Produce implements Runnable {

    private final int id;
    private Warehouse warehouse;

    public Produce(int id, Warehouse warehouse) {
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
            warehouse.produce(new Product());
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private final int id;
    private Warehouse warehouse;

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

    }
}

/**
 * Created by 100 on 2015/3/2.
 */
public class ProducerAndConsumer {

}
