package com.pep.platform.rbac.service.password;

/**
 * 密码编码对比器
 *
 * @author gang.liu
 */
public interface PasswordEncode {

    /**
     * 密码编码
     *
     * @param password 密码
     * @return 编码之后的密码
     */
    String encode(String password);

    /**
     * 原始密码与编码密码对比结果
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 编码密码
     * @return 匹配结果
     */
    boolean matches(String rawPassword, String encodedPassword);
}
