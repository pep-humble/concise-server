package com.pep.auth.configuration;

import com.pep.auth.external.ResourceOwnerLoginConfigurer;
import com.pep.auth.helper.JdbcUserDetailsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 认证服务配置器
 *
 * @author gang.liu
 */
@Configuration
@EnableWebSecurity
public class AuthServerConfiguration {

    /**
     * web端拦截器链
     *
     * @param httpSecurity http安全配置器
     * @return web端拦截器链
     * @throws Exception 可能抛出的异常
     */
    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        ApplicationContext applicationContext = httpSecurity.getSharedObject(ApplicationContext.class);
        httpSecurity
                // 关闭csrf拦截器
                .csrf().disable()
                .requestMatcher(new AntPathRequestMatcher("/web/**"))
                // 所有路径都需要认证
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .userDetailsService(applicationContext.getBean(UserDetailsService.class))
                .apply(new ResourceOwnerLoginConfigurer<>());
        return httpSecurity.build();
    }

    /**
     * 用户信息加载器
     *
     * @return 用户信息加载器
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new JdbcUserDetailsService();
    }

    /**
     * 临时使用不编码的密码编码器
     *
     * @return 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
