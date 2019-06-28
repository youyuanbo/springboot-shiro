package com.xiaoyou.springshiro.dao;

import com.xiaoyou.springshiro.bean.ShiroUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroUserMapperTest {

    @Autowired
    ShiroUserMapper shiroUserMapper;

    @Test
    public void test01() {
        ShiroUser shiroUser = shiroUserMapper.selectByPrimaryKey(1);
        System.out.println(shiroUser);



    }

}
