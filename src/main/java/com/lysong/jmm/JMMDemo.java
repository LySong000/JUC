package com.lysong.jmm;

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Author: LySong
 * @Date: 2020/3/30 20:15
 */
public class JMMDemo {
    //加volatile可以保证可见性
    private volatile static int num = 0;
    public static void main(String[] args) {

        new Thread(() -> {
            //线程1 不知道主内存的变化
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);
    }
}
