package com.lysong.pool;

import java.util.concurrent.*;

/**
 * 使用线程池创建线程
 * Executors 工具类 3大方法
 * 最大线程如何定义：
 * 1.CPU密集型：密集型，几核CPU就是几，可以保持CPU效率最高
 * 2.IO密集型：密集型，判断程序中十分耗IO的线程，
 * @Author: LySong
 * @Date: 2020/3/28 20:54
 */
public class Demo01 {
    public static void main(String[] args) {
        //获取CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            //最大承载：Deque + max
            for (int i = 0; i < 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完关闭线程池
            threadPool.shutdown();
        }

    }
}
