package com.pep.auth.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ping接口
 *
 * @author gang.liu
 */
@RequestMapping("/web")
@RestController
public class PingController {

    /**
     * ping接口
     *
     * @return ping pong
     */
    @GetMapping("/ping")
    public String ping() {
        return "ping~~~pong~~~";
    }
}
