package com.example.demo.exercise.design_pattern.produceconsume;

//描述产品
class Product {
    private String name;
    private int count;
    private boolean flag;

    // 生产产品的功能
    public synchronized void produce(String name) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = count + "个" + name;
        System.out.println(Thread.currentThread().getName() + "生产了" + this.name);
        count++;
        flag = true;
        notifyAll();
    }

    // 消费产品的功能
    public synchronized void consume() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费了" + this.name);
        flag = false;
        notifyAll();
    }

}