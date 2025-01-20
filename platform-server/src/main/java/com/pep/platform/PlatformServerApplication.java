package com.pep.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 平台基础服务
 *
 * @author liu.gang
 */
@SpringBootApplication
public class PlatformServerApplication {

    /**
     * 启动方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(PlatformServerApplication.class, args);
    }

}
