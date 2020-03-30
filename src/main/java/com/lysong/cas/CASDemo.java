package com.lysong.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LySong
 * @Date: 2020/3/30 22:05
 */
public class CASDemo {

    //CAS compareAndSet：比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //期望，更新
        //compareAndSet(int expect, int update)
        //如果期望值符合就更新
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

    }
}
