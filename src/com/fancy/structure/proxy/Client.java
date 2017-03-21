/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import com.fancy.dal.RoleDao;
import com.fancy.dal.UserDao;
import com.fancy.structure.adapter.Autowired;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiangjiaze
 * @version Id: Client.java, v 0.1 2017/3/21 19:04 FancyKong Exp $$
 */
public class Client {

    private Map<Class<?>, Object> ioc = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException {
        Client app = new Client();
        app.init();
        app.logic();
    }

    private void logic() {
        System.out.println(userDao);
        System.out.println(roleDao);
        roleDao.selectById(1);
        roleDao.selectList();
        userDao.selectOne();
        userDao.selectList();
    }

    private String daoPackageName;
    private MapperRegistry registry;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private void init() throws ClassNotFoundException, SQLException, IllegalAccessException {
        daoPackageName = "com.fancy.dal";
        registry = new MapperRegistry();
        initDal();
        inject(this);
    }

    private void inject(Object obj) throws IllegalAccessException {
        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();
        for(Field field :fields){
            if(field.getAnnotation(Autowired.class) != null){
                field.setAccessible(true);
                field.set(obj,ioc.get(field.getType()));
            }
        }
    }

    private Object getObject(Class<?> clz) throws SQLException {
        return registry.getMapper(clz,getConnection());
    }

    Map<String,Class<?>> entitys = new HashMap<>();
    List<Class<?>> daos = new ArrayList<>();
    private void initDal() throws ClassNotFoundException, SQLException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(daoPackageName.replace(".","/"));
        File packageDir = new File(url.getPath());

        for(File classFile : packageDir.listFiles()){
            String className = daoPackageName+"."+classFile.getName().replace(".class","");
            Class clz = Class.forName(className);
            if(clz.getAnnotation(Entity.class)!= null){
                entitys.put(clz.getSimpleName(),clz);
            }else if(clz.getAnnotation(Mapper.class) != null){
                daos.add(clz);
            }
        }
        for(Class<?> clz : daos){
            registry.addMappers(clz);
            ioc.put(clz,getObject(clz));
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("fancy0528");
        connection = dataSource.getConnection();
        return connection;
    }
}
