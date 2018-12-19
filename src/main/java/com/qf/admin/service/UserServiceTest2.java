package com.qf.admin.service;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest2 {
    @Test
    public void save() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-service2.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.save();
    }

}