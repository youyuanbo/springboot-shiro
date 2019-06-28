package com.xiaoyou.springshiro.shiro;

import com.xiaoyou.springshiro.bean.ShiroUser;
import com.xiaoyou.springshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加授权
//        simpleAuthorizationInfo.addStringPermission("user:add");

        //获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        //根据id来查询
        ShiroUser shiroUser1 = userService.queryById(shiroUser.getId());

        //添加授权
        simpleAuthorizationInfo.addStringPermission(shiroUser1.getPerms());

        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //判断用户名与密码是否正确

        //1、将authenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        ShiroUser shiroUser = userService.getOne(token.getUsername());

        //2、判断用户名
        if (shiroUser == null) {
            //放回null，shiro会抛出UnKnowAccountException
            return null;
        }

        //3、判断密码，Shiro会制动判断密码是否一致
        /**
         * 第一个参数：需要返回给Login方法的一些数据
         * 第二个参数：密码（数据库中存储的密码）
         * 第三个参数：Shiro的名字
         */
        return new SimpleAuthenticationInfo(shiroUser, shiroUser.getPassword(), "");
    }
}
