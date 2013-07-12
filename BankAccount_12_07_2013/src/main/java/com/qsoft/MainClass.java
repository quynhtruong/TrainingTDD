package com.qsoft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: quynhtq
 * Date: 7/9/13
 * Time: 2:39 PM
 */
public class MainClass
{
    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    }
}
