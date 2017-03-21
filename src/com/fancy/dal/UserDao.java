/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.dal;

import com.fancy.structure.proxy.Mapper;
import com.fancy.structure.proxy.SQL;

import java.util.List;

/**
 * @author Jiangjiaze
 * @version Id: UserDao.java, v 0.1 2017/3/21 19:05 FancyKong Exp $$
 */
@Mapper
public interface UserDao {
    @SQL(value = "select * from user limit 0,1",type = "select")
    User selectOne();

    @SQL(value = "select * from user",type = "select")
    List<User> selectList();
}
