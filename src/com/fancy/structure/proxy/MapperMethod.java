/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jiangjiaze
 * @version Id: MapperMethod.java, v 0.1 2017/3/21 18:29 FancyKong Exp $$
 */
public class MapperMethod {
    private String sql;
    private String type;
    private Class returnType;

    public MapperMethod(Method method) {
        SQL sql = method.getAnnotation(SQL.class);
        this.sql = sql.value();
        this.type = sql.type();
        returnType = method.getReturnType();
    }

    public Object excute(Connection connection, Object[] args){
        //业务逻辑,也就是mybatis中SqlSession.selectOne()那些方法的调用
        System.out.println(sql);
        return null;
    }
}
