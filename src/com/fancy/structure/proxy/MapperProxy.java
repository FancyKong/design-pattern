/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author Jiangjiaze
 * @version Id: MapperProxy.java, v 0.1 2017/3/21 18:28 FancyKong Exp $$
 */
public class MapperProxy implements InvocationHandler {
    private Map<Method,MapperMethod> cacheMethods;
    private Connection connection;

    public MapperProxy(Map<Method, MapperMethod> cacheMethods, Connection connection) {
        this.cacheMethods = cacheMethods;
        this.connection = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().equals(Object.class)){
            return method.invoke(this,args);
        }
        MapperMethod mapperMethod = cacheMethods.computeIfAbsent(method, k -> new MapperMethod(method));
        return mapperMethod.excute(connection,args);
    }
}
