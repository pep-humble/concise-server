package com.pep.concise.business.platform.rbac.service;


import com.pep.concise.business.platform.rbac.web.form.UserCreateForm;

/**
 * 用户服务
 *
 * @author gang.liu
 */
public interface UserService {

    void createUser(UserCreateForm createForm);
}
