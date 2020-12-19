package com.example.demo.exercise.design_pattern.produceconsume;

// 消费任务
class Consumer implements Runnable {
    private Product pro;

    public Consumer(Product pro) {
        this.pro = pro;

    }

    public void run() {
        while (true) {
            pro.consume();
        }
    }
}
