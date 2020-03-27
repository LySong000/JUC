package com.lysong.pc;

/**
 * 线程之间的通信问题生产者和消费者问题
 * 线程交替执行 A B 操作同一个变量 num = 0
 * A num + 1
 * B num - 1
 * @Author: LySong
 * @Date: 2020/3/24 19:59
 */
public class A {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

/**
 * 判断等待，业务，通知
 */
class Data{
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while(num != 0){
            //等待
            this.wait();
        }
        num ++;
        System.out.println(Thread.currentThread().getName()+"=>" + num);
        //通知其他线程
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while(num == 0){
            this.wait();
        }
        num --;
        System.out.println(Thread.currentThread().getName()+"=>" + num);
        //通知其他线程
        this.notifyAll();
    }
}