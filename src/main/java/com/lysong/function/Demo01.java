package com.lysong.function;

import java.util.function.Function;

/**
 * Function 函数型接口
 * 只要是函数式接口可以用lambda简化
 * @Author: LySong
 * @Date: 2020/3/28 21:49
 */
public class Demo01 {
    public static void main(String[] args) {
        //工具类，输出输入的值
        Function function = (str) -> {return str;};
        System.out.println(function.apply("abb"));
    }
}
