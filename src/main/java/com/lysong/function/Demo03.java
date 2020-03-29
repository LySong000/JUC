package com.lysong.function;

import java.util.function.Consumer;

/**
 * @Author: LySong
 * @Date: 2020/3/28 22:02
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("hello");
    }
}
