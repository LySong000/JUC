package com.lysong.function;

import java.util.function.Supplier;

/**
 * @Author: LySong
 * @Date: 2020/3/28 22:04
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> {
            return "hello";
        };
        System.out.println(supplier.get());
    }
}
