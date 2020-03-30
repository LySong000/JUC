package com.lysong.single;

import java.lang.reflect.Constructor;

/**
 * 枚举本身就是一个Class类
 * @Author: LySong
 * @Date: 2020/3/30 21:51
 */
public enum EnumSingle {

    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}
class Test{

    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1 == instance2);

    }
}
