package com.xiaoyou.springshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyou.springshiro.dao")
public class SpringshiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringshiroApplication.class, args);
    }

}
