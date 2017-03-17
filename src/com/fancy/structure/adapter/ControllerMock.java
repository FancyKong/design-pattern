/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.adapter;

/**
 * @author Jiangjiaze
 * @version Id: ControllerMock.java, v 0.1 2017/3/16 22:08 FancyKong Exp $$
 */
@Controller
public class ControllerMock {

    @RequestMapping("/user/age")
    public String userInfo(){
        return "name:fancy,age:123";
    }

    @RequestMapping("/user/gender")
    public String userGender(){
        return "name:fancy,gender:Man";
    }
}
