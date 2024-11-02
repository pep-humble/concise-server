package com.pep.concise.business.platform.rbac.service.impl;

import com.pep.concise.business.platform.rbac.service.UserService;
import com.pep.concise.business.platform.rbac.web.form.UserCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author gang.liu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(UserCreateForm createForm) {

    }
}
