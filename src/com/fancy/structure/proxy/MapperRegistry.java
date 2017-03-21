/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangjiaze
 * @version Id: Configuration.java, v 0.1 2017/3/21 18:51 FancyKong Exp $$
 */
public class MapperRegistry {
    private Map<Class<?>,MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> void addMappers(Class<T> ifc){
        knownMappers.put(ifc,new MapperProxyFactory<>(ifc));
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> ifc,Connection connection){
        MapperProxyFactory<T> mpf = (MapperProxyFactory<T>) knownMappers.get(ifc);
        return mpf.newInstance(connection);
    }

}
