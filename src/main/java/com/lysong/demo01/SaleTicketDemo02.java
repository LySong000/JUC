package com.lysong.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程就是一个单独的资源类，没有任何附属的操作，降低耦合性
 * 1.属性，方法
 * @Author: LySong
 * @Date: 2020/3/22 22:58
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发，多线程操作同一个资源类
        Ticket2 ticket = new Ticket2();

        //JDK1.8后，lambda表达式
        new Thread(() -> { for (int i = 1; i < 60; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 1; i < 60; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 1; i < 60; i++) ticket.sale(); }, "C").start();
    }
}

/**
 * 资源类
 */
class Ticket2{
    private int number = 30;

    //可重入锁
    Lock lock = new ReentrantLock();

    /**
     * 卖票的方式
     * synchronized本质，队列，锁
     */
    public void sale(){
        lock.lock();
        lock.tryLock();

        try {
            //业务代码写在这
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number --) + "票，剩余:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}