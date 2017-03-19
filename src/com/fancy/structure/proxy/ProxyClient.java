/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import com.fancy.structure.adapter.Autowired;
import com.fancy.structure.adapter.Controller;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.lang.model.SourceVersion;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Jiangjiaze
 * @version Id: ProxyClient.java, v 0.1 2017/3/17 16:33 FancyKong Exp $$
 */
@Controller
public class ProxyClient {
    @Autowired
    UserDao dao;

    private void service() {
        System.out.println("dao.userListSize() = " + dao.userListSize());
        System.out.println("dao.selectList() = " + dao.selectList());
        System.out.println("dao.updateById(\"nimahai\",1) = " + dao.updateById("nimahai", 1));
        System.out.println("dao.deleteById(1) = " + dao.deleteById(2));
        System.out.println("dao.seleteById(1) = " + dao.seleteById(1));
        System.out.println("dao.insert() = " + dao.insert("cao","CURDATE()"));
    }

    public static void main(String[] args) {
        initContext();
        ProxyClient p = (ProxyClient) ioc.get(ProxyClient.class);
        p.service();
    }

    static Map<Class, Object> ioc = new HashMap<>();

    private static void initContext() {
        initDao();
        URL url = Thread.currentThread().getContextClassLoader().getResource("com/fancy/structure/proxy");
        File file = new File(url.getPath());
        for (File f : file.listFiles()) {
            try {
                if (f.isFile()) {
                    Class c = Class.forName("com.fancy.structure.proxy" + "." + f.getName().replace(".class", ""));
                    if (c.getAnnotation(Controller.class) != null) {
                        ioc.put(c, c.newInstance());
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        for (Class bean : ioc.keySet()) {
            for (Field field : bean.getDeclaredFields()) {
                if (field.getAnnotation(Autowired.class) != null) {
                    try {
                        field.setAccessible(true);
                        field.set(ioc.get(bean), ioc.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void initDao() {
        try {
            initEntity();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserDao userDao = (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , new Class[]{UserDao.class}
                , (proxy, method, args) -> {
                    SQL sqlA = method.getAnnotation(SQL.class);
                    String type = sqlA.type();
                    String sql = sqlA.value();
                    if(args != null && args.length > 0)
                    for(Object object : args){
                        sql = sql.replaceFirst("\\?",object.toString());
                    }
                    Class clz = method.getReturnType();
                    Connection con = getConnection();
                    String entiString = getEntityString(sql).toUpperCase();//拿到user
                    Class entity = entites.get(entiString);
                    switch (type){
                        case "select":
                            ResultSet rs = con.prepareStatement(sql).executeQuery();
                            if (clz.equals(List.class)) {
                                List list = new ArrayList();
                                while (rs.next()) {
                                    Object entityObj = entity.newInstance();
                                    for (Field field : entity.getDeclaredFields()) {
                                        field.setAccessible(true);
                                        field.set(entityObj, rs.getObject(field.getName()));
                                    }
                                    list.add(entityObj);
                                }
                                return list;
                            } else if(int.class.equals(clz)){
                                int res = 0;
                                while (rs.next()) res = rs.getInt(1);
                                return res;
                            } else if(long.class.equals(clz)){
                                long res = 0;
                                while (rs.next()) res = rs.getLong(1);
                                return res;
                            }else{
                                Object entityObj = entity.newInstance();
                                while (rs.next()){
                                    for(Field f : entity.getDeclaredFields()){
                                        f.setAccessible(true);
                                        f.set(entityObj,rs.getObject(f.getName()));
                                    }
                                }
                                return entityObj;
                            }
                        case "update":
                        case "insert":
                        case "delete":
                            return con.prepareStatement(sql).executeUpdate();
                        default:
                            throw new IllegalArgumentException("not supported type");
                    }
                });
        //User user = userDao.seleteById(1);
        ioc.put(UserDao.class, userDao);
    }

    private static String getEntityString(String sql) {
        int i = sql.indexOf("from") + 4;
        int st = 0;
        int end = sql.length();
        for (int j = i; j < sql.length(); j++) {
            if (sql.charAt(j) == ' ') {
                st = j+1;
            } else {
                for (int k = st + 1; k < sql.length(); k++) {
                    if (sql.charAt(k) == ' ') {
                        end = k;
                        break;
                    }
                }
                break;
            }
        }
        return sql.substring(st, end);
    }

    static Map<String, Class> entites = new HashMap<>();

    private static void initEntity() throws ClassNotFoundException {
        String packageName = "com.fancy.structure.proxy";//retains
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        File packageDir = new File(url.getPath());
        for (File classFile : packageDir.listFiles()) {
            if (!classFile.isDirectory()) {
                Class clz = Class.forName(packageName + "." + classFile.getName().split("\\.")[0]);
                if (clz.getAnnotation(Entity.class) != null) {
                    entites.put(clz.getSimpleName().toUpperCase(), clz);
                }
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("fancy0528");
        return dataSource.getConnection();
    }
}

