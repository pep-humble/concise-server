package com.pep.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证模块启动类
 *
 * @author liu.gang
 */
@SpringBootApplication
public class AuthServerApplication {

    /**
     * 启动方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
