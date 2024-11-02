package com.pep.concise.auth.jwt;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * jwt形式令牌配置
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class JwtTokenProperty {

    /**
     * 秘钥
     */
    private String signature;

    /**
     * 访问令牌存活时间
     */
    private String accessTokenTtl;

    /**
     * 刷新令牌存活时间
     */
    private String refreshTokenTtl;
}
