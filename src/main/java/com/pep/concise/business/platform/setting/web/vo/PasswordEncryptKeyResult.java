package com.pep.concise.business.platform.setting.web.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 密码加密公钥键
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class PasswordEncryptKeyResult {

    /**
     * 公钥
     */
    private String publicKey;

}
