package com.lysong.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 6.一个静态同步方法，一个普通同步方法
 * @Author: LySong
 * @Date: 2020/3/24 20:54
 */
public class Test4 {
    public static void main(String[] args) {
        //两个对象因为是静态的只有一个Class锁
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            phone1.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        },"B").start();
    }

}

//Phone3唯一的一个class对象
class Phone4{

    //synchronized锁的对象是方法的调用者，谁先拿到谁先执行
    //static 静态方法
    //类一加载就有了！ Class模板
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通的同步方法
    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }

}