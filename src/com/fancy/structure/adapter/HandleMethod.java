/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Jiangjiaze
 * @version Id: HandleMethod.java, v 0.1 2017/3/16 22:11 FancyKong Exp $$
 */
public class HandleMethod {
    /**
     * 拦截路径
     */
    private String pattern;
    /**
     * 执行的方法
     */
    private Method method;
    /**
     * 方法的参数
     */
    private Parameter[] parameters;
    /**
     * 调用该方法的对象
     */
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public Object invoke() {
        try {
            return method.invoke(obj,parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("??");
        }
    }
}
