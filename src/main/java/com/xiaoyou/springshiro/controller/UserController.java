package com.xiaoyou.springshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello xiaoyou";
    }


    @GetMapping("/test")
    public String testThymeleaf(Model model){
        model.addAttribute("name", "xiaoyou2");
        return "test";
    }

    @GetMapping("/add")
    public String add(){
        return "user/add";
    }

    @GetMapping("/update")
    public String update(){
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/unAuthorized")
    public String unAuthorized(){
        return "unAuthorized";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        //使用shiro实现认证操作

        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户名与密码
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(username);
        token.setPassword(password.toCharArray());

        //3、执行登录方法
        try {
            subject.login(token);
            //登录成功，到test.html页面
            return "redirect:/test";
        }catch (UnknownAccountException e){
            //登录失败，用户名不存在
            model.addAttribute("message", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败，密码错误
            model.addAttribute("message", "密码错误");
            return "login";
        }
    }

}
