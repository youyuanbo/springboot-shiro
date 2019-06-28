package com.xiaoyou.springshiro.service.impl;

import com.xiaoyou.springshiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void queryByName() {
        String name = "xiaoyou";

    }

    @Test
    public void getOne() {
    }

    @Test
    public void getPerms() {
        String perms = userService.getPerms("xiaoyou", "hello");
        System.out.println(perms);
    }
}