package com.qf.admin.dao.impl;

import com.qf.admin.dao.UserDao;
import com.qf.admin.pojo.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO层实现类的两种做法：
 * 1. 继承自JdbcDaoSupport，如上课视频的做法
 * 2. 普通POJO，如下类的做法
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<UserInfo> listUsers() {
        return null;
    }

    @Override
    public int deleteUserById(int id) {
        return jdbcTemplate.update("delete from tb_user where id=?",id);
    }

    @Override
    public UserInfo getUserById(int id) {
        String sql = "select * from tb_user where id=? ";
        return (UserInfo) jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return mapRowHandler(resultSet);
            }
        });
    }

    private UserInfo mapRowHandler(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(resultSet.getInt("id"));
        userInfo.setUname(resultSet.getString("uname"));
        userInfo.setUage(resultSet.getInt("uage"));
        return userInfo;
    }

    @Override
    public int saveUser(UserInfo userInfo) {
        return jdbcTemplate.update("insert into tb_user(uname,uage) values(?,?)",userInfo.getUname(),userInfo.getUage());
    }
}
