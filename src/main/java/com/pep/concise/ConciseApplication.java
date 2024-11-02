package com.pep.concise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 启动类
 *
 * @author liu.gang
 */
@EnableWebSecurity(debug = true)
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class ConciseApplication {

    /**
     * 启动方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ConciseApplication.class, args);
    }
}
