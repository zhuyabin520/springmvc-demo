package com.qf.admin.jdbc.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


public class JdbcTest {

    @Test
    public void test1() throws Exception {
        //获取数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shop");
        dataSource.setUser("root");
        dataSource.setPassword("dhcdhc");
        //获取jdbc模板
        JdbcTemplate template = new JdbcTemplate(dataSource);
        //
        String sql = "insert into tb_user(uname,uage) values(?,?)";
        template.update(sql, "xiaoming", 12);
    }
}
