/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.dal;

import com.fancy.structure.proxy.Entity;

/**
 * @author Jiangjiaze
 * @version Id: Role.java, v 0.1 2017/3/21 19:07 FancyKong Exp $$
 */
@Entity
public class Role {
    private int id;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
