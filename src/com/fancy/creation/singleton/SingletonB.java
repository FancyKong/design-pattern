/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.creation.singleton;

/**
 * @author Jiangjiaze
 * @version Id: SingletonB.java, v 0.1 2017/3/16 13:43 FancyKong Exp $$
 */
public class SingletonB {
    private SingletonB(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static SingletonB singletonB;
    public static SingletonB getSingletonB(){
        if(singletonB==null){
            synchronized (SingletonB.class){
                if(singletonB == null)
                    singletonB = new SingletonB();
            }
        }
        return singletonB;
    }
}
