/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangjiaze
 * @version Id: MapperProxyFactory.java, v 0.1 2017/3/21 18:35 FancyKong Exp $$
 */
public class MapperProxyFactory<T> {
    private Map<Method,MapperMethod> cacheMethod = new HashMap<>();

    private Class<T> clz;

    public MapperProxyFactory(Class<T> itf) {
        this.clz = itf;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(Connection connection){
        return (T) Proxy.newProxyInstance(clz.getClassLoader(),
                new Class[]{clz},
                new MapperProxy(cacheMethod,connection));
    }
}
