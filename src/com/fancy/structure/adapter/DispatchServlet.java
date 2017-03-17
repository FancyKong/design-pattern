/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package com.fancy.structure.adapter;

/**
 * @author Jiangjiaze
 * @version Id: DispatchServlet.java, v 0.1 2017/3/16 22:13 FancyKong Exp $$
 */
public class DispatchServlet {

    public void service(String pattern,Response response){
        response.setReturnValue(AdapterClient.handleMethods.get(pattern).invoke());
    }
}
