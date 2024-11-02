package com.pep.concise.business.platform.rbac.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * spring security框架需要的用户信息服务
 *
 * @author gang.liu
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                "admin",
                "$2a$10$ZRuAb078aPo13hgVhq9.xuFbaeWAjagcaXVVpp2hUJ1cEkYNorEO6",
                Collections.emptyList()
        );
    }
}
