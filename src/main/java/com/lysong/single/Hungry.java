package com.lysong.single;

import sun.dc.pr.PRError;

/**
 * @Author: LySong
 * @Date: 2020/3/30 21:31
 */
public class Hungry {

    //浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private Hungry(){

    }

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
