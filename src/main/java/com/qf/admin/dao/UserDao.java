package com.qf.admin.dao;

import com.qf.admin.pojo.po.UserInfo;

import java.util.List;

public interface UserDao {

    List<UserInfo> listUsers();

    int deleteUserById(int id);

    UserInfo getUserById(int id);

    int saveUser(UserInfo userInfo);
}
