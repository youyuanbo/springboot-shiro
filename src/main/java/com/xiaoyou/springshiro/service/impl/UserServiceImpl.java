package com.xiaoyou.springshiro.service.impl;

import com.xiaoyou.springshiro.bean.ShiroUser;
import com.xiaoyou.springshiro.bean.ShiroUserExample;
import com.xiaoyou.springshiro.dao.ShiroUserMapper;
import com.xiaoyou.springshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ShiroUserMapper shiroUserMapper;

    @Override
    public ShiroUser getOne(String username) {
        ShiroUserExample shiroUserExample = new ShiroUserExample();
        ShiroUserExample.Criteria criteria = shiroUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<ShiroUser> shiroUserList = shiroUserMapper.selectByExample(shiroUserExample);
        if (shiroUserList.size() == 0){
            return null;
        }
        return shiroUserList.get(0);

    }

    @Override
    public String getPerms(String username, String password) {

        ShiroUserExample shiroUserExample = new ShiroUserExample();
        ShiroUserExample.Criteria criteria = shiroUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<ShiroUser> shiroUserList = shiroUserMapper.selectByExample(shiroUserExample);
        System.out.println(shiroUserList.size());

        if (shiroUserList.size() == 0){
            return null;
        }

        return shiroUserList.stream().map(ShiroUser::getPerms).findFirst().get();

    }

    @Override
    public ShiroUser queryById(Integer id) {
        ShiroUser shiroUser = shiroUserMapper.selectByPrimaryKey(id);
        return shiroUser;
    }

}
