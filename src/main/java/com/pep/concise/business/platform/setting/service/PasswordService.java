package com.pep.concise.business.platform.setting.service;


import com.pep.concise.business.platform.setting.web.form.PasswordDecryptForm;

/**
 * 密码服务
 *
 * @author gang.liu
 */
public interface PasswordService {

    /**
     * 获取加密公钥
     *
     * @param requestKey 请求唯一编号
     * @return 加密公钥
     */
    String encryptPublicKey(String requestKey);

    /**
     * 对表单提交过来的密码进行解密
     *
     * @param decryptForm 加密表单
     * @return 解密后的密码
     */
    String decryptPassword(PasswordDecryptForm decryptForm);

}
