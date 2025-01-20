package com.pep.platform.rbac.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 员工创建表单
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class EmployeeCreateForm {

    /**
     * 登录账户
     */
    @NotBlank
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 平台昵称
     */
    @NotBlank
    private String nickname;
}
