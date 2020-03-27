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
public class C{

    public static void main(String[] args) {
        
        Data3 data3 = new Data3();
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}

/**
 * 资源类
 * A执行完调用B，B执行完调用C
 */
class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1;
    public void printA(){
        lock.lock();
        try {
            while (number != 1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() +"=>A");
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() +"=>B");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() +"=>C");
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}