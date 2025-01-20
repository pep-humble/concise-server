package com.pep.auth.external;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 资源拥有者登录配置器
 *
 * @author gang.liu
 */
public class ResourceOwnerLoginConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<ResourceOwnerLoginConfigurer<H>, H> {

    @Override
    public void configure(H builder) {
        builder.addFilterBefore(new ResourceOwnerLoginFilter(builder.getSharedObject(AuthenticationManager.class)), UsernamePasswordAuthenticationFilter.class);
    }
}
