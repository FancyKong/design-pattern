/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.creation.singleton;

/**
 * @author Jiangjiaze
 * @version Id: SingletonA.java, v 0.1 2017/3/16 12:56 FancyKong Exp $$
 */
public class SingletonA {
    private static SingletonA singletonA = new SingletonA();
    private SingletonA(){}
    public static SingletonA getSingletonA(){
        return singletonA;
    }

}
