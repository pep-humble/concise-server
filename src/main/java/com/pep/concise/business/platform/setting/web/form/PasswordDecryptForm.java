package com.pep.concise.business.platform.setting.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 密码解密表单
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class PasswordDecryptForm {

    /**
     * 请求唯一编号
     */
    private String requestKey;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 公钥加密aes key的密文
     */
    private String ciphertext;
}
