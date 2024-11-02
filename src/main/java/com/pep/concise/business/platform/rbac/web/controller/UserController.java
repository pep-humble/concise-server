package com.pep.concise.business.platform.rbac.web.controller;

import com.pep.concise.business.platform.rbac.service.UserService;
import com.pep.concise.business.platform.rbac.web.form.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用户控制器
 *
 * @author gang.liu
 */
@RestControllerAdvice
@RequestMapping("/rbac/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 创建用户
     *
     * @param createForm 创建表单
     */
    @PreAuthorize("hasRole('write')")
    @PostMapping("/create")
    public void createUser(@RequestBody @Validated UserCreateForm createForm) {
        userService.createUser(createForm);
    }

    @PreAuthorize("hasAnyAuthority('rbac:user:reset:password')")
    @PostMapping("/reset/password")
    public void resetPassword(){
        System.out.println("resetPassword");
    }
}
