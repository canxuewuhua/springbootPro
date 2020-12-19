package com.example.demo.exercise.design_pattern.produceconsume;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Product pro = new Product();

        Producer producer = new Producer(pro);
        Consumer consumer = new Consumer(pro);

        Thread t0 = new Thread(producer);
        Thread t1 = new Thread(producer);

        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(consumer);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

    }
}
