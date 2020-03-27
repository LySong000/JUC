package com.lysong.lock8;

import java.security.Permission;
import java.util.concurrent.TimeUnit;

/**
 * 8锁就是关于锁的8个问题
 * 1.标准情况下，两个线程先打印 发短信还是打电话？ 1/发短信 2/打电话
 * 2.发短信块延迟4秒，  1/发短信 2/打电话
 * @Author: LySong
 * @Date: 2020/3/24 20:54
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        },"B").start();
    }

}

class Phone{

    //synchronized锁的对象是方法的调用者，谁先拿到谁先执行
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

}