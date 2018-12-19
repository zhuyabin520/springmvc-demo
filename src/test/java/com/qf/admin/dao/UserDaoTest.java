package com.qf.admin.dao;

import com.qf.admin.pojo.po.UserInfo;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserDaoTest {
    @Test
    public void deleteUserById() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);
        UserInfo userInfo = new UserInfo("xdd", 80);
        userDao.saveUser(userInfo);
    }

}