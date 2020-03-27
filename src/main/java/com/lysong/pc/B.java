package com.lysong.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题生产者和消费者问题
 * 线程交替执行 A B 操作同一个变量 num = 0
 * A num + 1
 * B num - 1
 * @Author: LySong
 * @Date: 2020/3/24 19:59
 */
public class B{

    public static void main(String[] args) {
        Data2 data = new Data2();
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
class Data2{
    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        try {
            lock.lock();
            while(num != 0){
                condition.await();
            }
            num ++;
            System.out.println(Thread.currentThread().getName()+"=>" + num);
            //通知其他线程
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
    public  void decrement() throws InterruptedException {
        try {
            lock.lock();
            while(num == 0){
                condition.await();
            }
            num --;
            System.out.println(Thread.currentThread().getName()+"=>" + num);
            //通知其他线程
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}