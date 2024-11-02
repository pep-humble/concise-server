package com.pep.concise.auth.token;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成令牌的视图
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class TokenResult {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

}
