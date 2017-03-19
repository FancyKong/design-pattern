/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy.stat;

/**
 * @author Jiangjiaze
 * @version Id: DoSomethingProxy.java, v 0.1 2017/3/18 0:35 FancyKong Exp $$
 */
public class ExtendProxy extends DoSomething{
    @Override
    public void dosomething() {
        System.out.println("before");
        super.dosomething();
        System.out.println("after");
    }
}
