package com.lysong.function;

import java.util.function.Predicate;

/**
 * Predicate 断定型接口
 * @Author: LySong
 * @Date: 2020/3/28 21:55
 */
public class Demo02 {
    public static void main(String[] args) {
        //判断字符串是否为空
        Predicate predicate = (str) -> {
            if(str.equals("")){
                return true;
            }else {
                return false;
            }
        };
        System.out.println(predicate.test("aaA"));
    }
}
