/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.creation.singleton;

/**
 * @author Jiangjiaze
 * @version Id: SingletonClient.java, v 0.1 2017/3/16 13:01 FancyKong Exp $$
 */
public class SingletonClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonA a = SingletonA.getSingletonA();
                System.out.println(a);
            }).start();
        }
        System.out.println("*************************");
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SingletonB b = SingletonB.getSingletonB();
                System.out.println(b);
            }).start();
        }
        System.out.println("*************************");
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SingletonC c = SingletonC.getSingletonC();
                System.out.println(c);
            }).start();
        }
    }
}
