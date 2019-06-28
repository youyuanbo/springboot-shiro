package com.xiaoyou.springshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xiaoyou.springshiro.shiro.UserRealm;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {

    //1、创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         * 配置Shiro内置过滤器，实现权限的拦截（url）
         * 常用的过滤器：
         * 1、anon：无需登录就可以访问】
         * 2、authc：必须认证才可以访问
         * 3、user：如果使用了RememberMe功能，可以直接访问
         * 4、perms：该资源必须授予权限，才可以访问
         * 5、role：该资源比喻得到角色权限才可以访问
         */

        //添加过滤器
        Map<String, String> filterMap = new LinkedMap();
        /*filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");
        */
        //放行一些请求
        filterMap.put("/test", "anon");
        filterMap.put("/login", "anon");

        //拦截添加操作，需要授权
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");

        //拦截一个目录下的url
        filterMap.put("/*", "authc");

//        shiroFilterFactoryBean.setUnauthorizedUrl("/test");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改拦截成功后的跳转页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //设置未授权路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");

        return shiroFilterFactoryBean;
    }


    //2、创建DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }


    //3、创建Realm
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }

    //配置ShiroDialect，用户thymeleaf与Shiro标签配合使用

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
