/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.adapter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiangjiaze
 * @version Id: AdapterClient.java, v 0.1 2017/3/16 22:20 FancyKong Exp $$
 */
public class AdapterClient {
    static Map<Class, Object> ioc = new HashMap<>();
    static Map<String, HandleMethod> handleMethods = new HashMap<>();

    public static void init(String packageName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url != null) {
            File file = new File(url.getPath());
            if (file.listFiles() != null) {
                for (File f : file.listFiles()) {
                    if (f.exists()) {
                        String clzName = f.getName().replace(".class", "");
                        Class clz = Class.forName(packageName + "." + clzName);
                        if (clz.getAnnotation(Controller.class) != null) {
                            ioc.put(clz, clz.newInstance());
                            Method[] methods = clz.getDeclaredMethods();
                            for (Method method : methods) {
                                if (method.getAnnotation(RequestMapping.class) != null) {
                                    HandleMethod handleMethod = new HandleMethod();
                                    handleMethod.setMethod(method);
                                    handleMethod.setParameters(method.getParameters());
                                    handleMethod.setPattern(method.getAnnotation(RequestMapping.class).value());
                                    handleMethod.setObj(ioc.get(clz));
                                    handleMethods.put(handleMethod.getPattern(), handleMethod);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String packageName = "com.fancy.structure.adapter";
        init(packageName);
        DispatchServlet d = new DispatchServlet();
        Response r = new Response();
        d.service("/user/age",r);
        System.out.println(r.getReturnValue());
    }
}
