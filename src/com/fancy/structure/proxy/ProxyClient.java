/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import com.fancy.structure.adapter.Autowired;
import com.fancy.structure.adapter.Controller;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

/**
 * @author Jiangjiaze
 * @version Id: ProxyClient.java, v 0.1 2017/3/17 16:33 FancyKong Exp $$
 */
@Controller
public class ProxyClient {
    @Autowired
    UserDao dao;

    private void service(){
        System.out.println("dao.userListSize() = " + dao.userListSize());

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
        for(File f : file.listFiles()){
            try {
                Class c = Class.forName("com.fancy.structure.proxy"+"."+f.getName().replace(".class",""));
                if(c.getAnnotation(Controller.class) != null){
                    ioc.put(c,c.newInstance());
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        for(Class bean : ioc.keySet()){
            for(Field field : bean.getDeclaredFields()){
                if(field.getAnnotation(Autowired.class)!= null){
                    try {
                        field.setAccessible(true);
                        field.set(ioc.get(bean),ioc.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void initDao() {
        UserDao userDao = (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , new Class[]{UserDao.class}
                , (proxy, method, args) -> {
                    String sql = method.getAnnotation(SQL.class).value();
                    System.out.println(sql);
                    Class type = method.getReturnType();
                    System.out.println("type.getTypeName() = " + type.getTypeName());
                    Connection connection = getConnection();
                    if(int.class.equals(type)){
                        int x = 0;
                        PreparedStatement p = connection.prepareStatement(sql);
                        ResultSet rs = p.executeQuery();
                        while (rs.next()) x = rs.getInt(1);
                        return x;
                    }
                    return 0;
                });
        //User user = userDao.seleteById(1);
        ioc.put(UserDao.class,userDao);
    }

    private static Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("fancy0528");
        return dataSource.getConnection();
    }
}

