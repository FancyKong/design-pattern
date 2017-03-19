/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy.stat;

/**
 * @author Jiangjiaze
 * @version Id: ComProxy.java, v 0.1 2017/3/18 0:38 FancyKong Exp $$
 */
public class ComProxy {
    private DoSomething doSomething;
    ComProxy(DoSomething doSomething){
        this.doSomething = doSomething;
    }

    public void doSomething(){
        System.out.println("before");
        doSomething.dosomething();
        System.out.println("After");
    }
}
