package com.qf.admin.service;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    @Test
    public void save() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-service.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        userService.save();

    }

}