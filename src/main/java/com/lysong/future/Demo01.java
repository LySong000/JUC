package com.lysong.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行
 * 成功回调
 * 失败回凋
 * @Author: LySong
 * @Date: 2020/3/29 20:50
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        //没有返回值的异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() ->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync => Void");
//        });
//
//        System.out.println("1111");
//
//        completableFuture.get();//阻塞执行结果
        //有返回值的异步回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync => Integer");
            int i = 10/0;
            return 1024;
        });

        completableFuture.whenComplete((t,u) -> {
            System.out.println("t->"+t);
            System.out.println("u->"+u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;
        }).get();
    }
}
