package com.pep.concise.auth.token;

import com.nimbusds.jose.JOSEException;
import org.springframework.security.core.Authentication;

/**
 * 令牌管理器
 *
 * @author gang.liu
 */
public interface TokenManager {

    /**
     * 根据认证结果生成令牌
     *
     * @param authentication 认证结果
     * @return 令牌
     */
    TokenResult generator(Authentication authentication) throws JOSEException;

    /**
     * 解析令牌
     *
     * @param accessToken 访问令牌
     * @return 认证信息
     */
    Authentication parse(String accessToken);

    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @return 刷新后生成的访问令牌以及新的刷新令牌
     */
    TokenResult refresh(String refreshToken);

    /**
     * 撤销令牌,使令牌失效
     *
     * @param accessToken 访问令牌
     */
    void revoke(String accessToken);

}
