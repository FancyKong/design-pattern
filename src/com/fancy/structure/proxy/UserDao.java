/**
 * JDKCC.com.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

/**
 * @author Jiangjiaze
 * @version Id: UserDao.java, v 0.1 2017/3/17 16:36 FancyKong Exp $$
 */
public interface UserDao {
    @SQL(value = "select * from user where id = #id")
    User seleteById(long id);

    @SQL("select count(*) from user")
    int userListSize();

    @SQL(value = "delete from user where id = ?",type = "delete")
    int deleteById(long id);
}
