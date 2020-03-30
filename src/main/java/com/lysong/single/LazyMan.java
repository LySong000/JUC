package com.lysong.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式单例
 * 道高一尺魔高一丈
 * @Author: LySong
 * @Date: 2020/3/30 21:33
 */
public class LazyMan {

    private static boolean lysong = false;

    private LazyMan(){
        synchronized (LazyMan.class){
            if (lysong == false){
                lysong = true;
            }else{
                throw new RuntimeException("反射破坏单例异常");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        //双重检测
        if(lazyMan == null){
            synchronized (LazyMan.class){
                if(lazyMan == null){
                    //不是一个原子性操作
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *  原本： 1 2 3
                     *  但是由于指令重排：1 3 2
                     * 此时 由于对象已经指向了内存空间，假如有线程B进入
                     * 就会认为此块内存空间已经有了，不为null
                     * 就会直接返回，这时返回值为null
                     */
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }
    //多线程并发
    public static void main(String[] args) throws Exception {
//       LazyMan instance = LazyMan.getInstance();

        Field lysong = LazyMan.class.getDeclaredField("lysong");
        lysong.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance1 = declaredConstructor.newInstance();
        lysong.set(instance1,false);
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance1 == instance2);
    }

}
