package com.pep.auth.web.controller;

import com.pep.auth.web.form.RegisterForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册接口
 *
 * @author gang.liu
 */
@RequestMapping("/register")
@RestController
public class RegisterController {

    /**
     * 注册接口
     *
     * @param registerForm 注册表单
     * @return 注册结果
     */
    @PostMapping("/account")
    public String register(@RequestBody RegisterForm registerForm) {
        return "register success";
    }

}
