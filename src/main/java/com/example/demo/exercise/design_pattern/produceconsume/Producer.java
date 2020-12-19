package com.example.demo.exercise.design_pattern.produceconsume;

// 生产任务
class Producer implements Runnable {
    private Product pro;

    public Producer(Product pro) {
        this.pro = pro;

    }

    public void run() {
        while (true) {
            pro.produce("笔记本");
        }

    }

}
