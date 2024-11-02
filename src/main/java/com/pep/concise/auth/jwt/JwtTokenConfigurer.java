package com.pep.concise.auth.jwt;

import com.pep.concise.auth.token.TokenManager;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义登录,登录成功返回双令牌
 *
 * @author gang.liu
 */
public class JwtTokenConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<CorsConfigurer<H>, H> {

    @Override
    public void configure(H http) {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler(applicationContext.getBean(TokenManager.class)));
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
        jwtAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
