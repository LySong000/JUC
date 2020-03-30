package com.lysong.unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 * @Author: LySong
 * @Date: 2020/3/30 20:33
 */
public class VDemo02 {
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
        //+1方法，CAS
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        //理论上num结果为20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
