package com.lysong.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: LySong
 * @Date: 2020/3/27 19:36
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //怎么启动callable
        //new Thread(new Runnable).start();
        //new Thread(new FutureTask<V>(Callable)).start();
        MyThread myThread = new MyThread();
        //适配类
        FutureTask<Integer> integerFutureTask = new FutureTask<>(myThread);
        new Thread(integerFutureTask,"A").start();
        //结果会被缓存
        new Thread(integerFutureTask,"B").start();
        //获取返回结果，可能会阻塞，放在最后
        Integer integer = integerFutureTask.get();
        //或者使用异步通信
        System.out.println(integer);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()");
        return 1024;
    }
}
