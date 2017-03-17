/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.creation.singleton;

/**
 * @author Jiangjiaze
 * @version Id: SingletonC.java, v 0.1 2017/3/16 14:13 FancyKong Exp $$
 */
public class SingletonC {
    private SingletonC(){

    }
    public static SingletonC getSingletonC(){
        return Holder.c;
    }
    private static class Holder{
        static SingletonC c = new SingletonC();
    }
}
