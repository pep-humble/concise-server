package com.pep.platform.rbac.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 员工信息视图
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class EmployeeVo {

    /**
     * 员工编号
     */
    private String id;

    /**
     * 登录账户
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 平台昵称
     */
    private String nickname;
}
