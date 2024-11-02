package com.pep.concise.business.platform.rbac.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 用户创建表单
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class UserCreateForm {

    /**
     * 账户名
     */
    @NotBlank
    private String account;

    /**
     * 昵称
     */
    @NotBlank
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    @Email
    @NotBlank
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 开放平台账号
     */
    @Valid
    private List<OpenPlatformAccount> openPlatformAccountList;
}
