/**
 * JDKCC.com.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.dal;


import com.fancy.structure.proxy.Mapper;
import com.fancy.structure.proxy.SQL;

import java.util.List;

/**
 * @author Jiangjiaze
 * @version Id: RoleDao.java, v 0.1 2017/3/21 19:05 FancyKong Exp $$
 */
@Mapper
public interface RoleDao {
    @SQL(value = "select * from role where id = ?",type = "select")
    Role selectById(long id);

    @SQL(value = "select * from role",type = "select")
    List<Role> selectList();
}
