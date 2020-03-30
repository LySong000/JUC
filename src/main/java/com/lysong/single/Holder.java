package com.lysong.single;

/**
 * 静态内部类
 * @Author: LySong
 * @Date: 2020/3/30 21:40
 */
public class Holder {

    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
