package com.pep.concise.auth;

import com.pep.concise.auth.jwt.JwtSecurityContextRepository;
import com.pep.concise.auth.jwt.JwtTokenConfigurer;
import com.pep.concise.auth.jwt.JwtTokenManager;
import com.pep.concise.auth.jwt.JwtTokenProperty;
import com.pep.concise.auth.token.DefaultHeaderBearerTokenResolver;
import com.pep.concise.auth.token.TokenManager;
import com.pep.concise.auth.token.TokenResolver;
import com.pep.concise.business.platform.rbac.service.impl.UserDetailsServiceImpl;
import com.pep.concise.common.path.RequestMatcherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 安全框架配置
 *
 * @author gang.liu
 */
@Configuration
public class SecurityConfiguration {

    /**
     * 安全框架拦截器链,由一系列filter构成,作用在servlet前面
     *
     * @param http 构建安全框架拦截器链构造器
     * @return 安全框架拦截器链
     * @throws Exception 可能抛出的异常
     */
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        return http
                .securityContext(securityContextConfigurer ->
                        securityContextConfigurer
                                .requireExplicitSave(true)
                                .securityContextRepository(
                                        new JwtSecurityContextRepository(
                                                applicationContext.getBean(TokenResolver.class),
                                                applicationContext.getBean(TokenManager.class),
                                                permitAllRequestMatcherInfo()
                                        )
                                )
                )
                // 禁用匿名
                .anonymous().disable()
                // 前后端分离,禁用csrf
                .csrf().disable()
                // 禁用表单验证
                .formLogin().disable()
                .userDetailsService(new UserDetailsServiceImpl())
                // 开启跨域拦截器,默认使用web mvc配置
                .cors()
                .and()
                // 使用无状态session,项目目前使用jwt
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 授权所有请求
                .authorizeRequests(authorizeRequestsCustomizer())
                // 自定义登录,返回双令牌模式
                .apply(new JwtTokenConfigurer<>())
                .and()
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer.authenticationEntryPoint(new AuthenticationResponseEntryPoint())
                )
                .build();
    }

    /**
     * 表达式url授权配置,这里主要配置免鉴权路径,根据方法进行鉴权的采用方法注解的方式
     *
     * @return 表达式url授权配置
     */
    private Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> authorizeRequestsCustomizer() {
        return
                expressionInterceptUrlRegistry -> {
                    permitAllRequestMatcherInfo().forEach(requestMatcherInfo ->
                            // 免鉴权
                            expressionInterceptUrlRegistry
                                    .antMatchers(requestMatcherInfo.getMethod(), requestMatcherInfo.getPatterns().toArray(new String[0]))
                                    .permitAll()
                    );
                    expressionInterceptUrlRegistry
                            // 其余所有都开启鉴权
                            .anyRequest().authenticated();
                };
    }

    /**
     * 免鉴权的路径配置
     *
     * @return 免鉴权的路径配置
     */
    @Bean
    @ConfigurationProperties(prefix = "business.auth.permit-all")
    public List<RequestMatcherInfo> permitAllRequestMatcherInfo() {
        return new ArrayList<>();
    }

    /**
     * 密码编码器
     *
     * @return 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt形式令牌配置
     *
     * @return jwt形式令牌配置
     */
    @Bean
    @ConfigurationProperties(prefix = "business.auth.token.jwt")
    public JwtTokenProperty jwtTokenProperty() {
        return new JwtTokenProperty();
    }

    /**
     * JWT形式令牌管理器
     *
     * @param applicationName  应用名称
     * @param jwtTokenProperty jwt形式令牌配置
     * @return JWT形式令牌管理器
     */
    @Bean
    public TokenManager tokenManager(@Value("${spring.application.name}") String applicationName,
                                     JwtTokenProperty jwtTokenProperty) {
        return new JwtTokenManager(applicationName, jwtTokenProperty);
    }

    /**
     * 令牌抽取器
     *
     * @return 令牌抽取器
     */
    @Bean
    public TokenResolver resolver() {
        return new DefaultHeaderBearerTokenResolver();
    }

}
