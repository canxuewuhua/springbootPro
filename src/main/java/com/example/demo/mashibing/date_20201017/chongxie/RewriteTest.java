package com.example.demo.mashibing.date_20201017.chongxie;

/**
 * 子类重写父类方法时，不能使用比父类中被重写的方法更严格的权限。
 */
public class RewriteTest {
    public static void main(String[] args) {
        Cat cat=new Cat();
        cat.shout();//调用重写后的shout()方法
    }
}
