package com.xiaoyou.springshiro.service;

import com.xiaoyou.springshiro.bean.ShiroUser;

public interface UserService {

    ShiroUser getOne(String username);


    String getPerms(String username, String password);

    ShiroUser queryById(Integer id);
}
