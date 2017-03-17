/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.proxy;

import java.util.Date;

/**
 * @author Jiangjiaze
 * @version Id: User.java, v 0.1 2017/3/17 16:38 FancyKong Exp $$
 */
public class User {
    private long id;
    private String name;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
