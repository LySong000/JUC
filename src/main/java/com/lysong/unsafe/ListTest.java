package com.lysong.unsafe;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: LySong
 * @Date: 2020/3/24 22:34
 */
public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList是不安全的
        /**
         * 解决方案
         * 1.Vector可以解决
         * 2.Collections.synchronizedList(new ArrayList<>())
         * 3.CopyOnWriteArrayList<>()写入时赋值  COW 计算机程序设计领域的优化策略
         *   多个线程调用的时候，List读取的时候，固定的，写入
         *   在写入的时候避免覆盖，造成数据问题
         *   比Vector好在哪里
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
