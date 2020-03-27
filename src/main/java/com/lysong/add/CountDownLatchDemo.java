package com.lysong.add;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 * @Author: LySong
 * @Date: 2020/3/27 20:13
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go Out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待计数器归零，然后再向下执行
        countDownLatch.await();
        System.out.println("Close Door");
    }
}
