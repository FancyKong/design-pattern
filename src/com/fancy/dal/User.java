/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.dal;

import com.fancy.structure.proxy.Entity;

/**
 * @author Jiangjiaze
 * @version Id: User.java, v 0.1 2017/3/21 19:07 FancyKong Exp $$
 */
@Entity
public class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
