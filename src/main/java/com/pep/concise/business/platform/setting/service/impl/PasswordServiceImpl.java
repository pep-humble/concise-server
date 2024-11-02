package com.pep.concise.business.platform.setting.service.impl;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import com.pep.concise.business.platform.setting.service.PasswordService;
import com.pep.concise.business.platform.setting.web.form.PasswordDecryptForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

/**
 * 密码服务
 *
 * @author gang.liu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    /**
     * 加密管理器
     */
    private final CacheManager cacheManager;

    /**
     * 密码加密业务:
     * 1. 后端生成密钥对,后端保存私钥,公钥返回前端
     * 2. 前端独立生成aes加密key,并且对密码进行aes加密,对aes加密key进行rsa加密
     * 3. 一并提交给后端
     * 4. 后端先试用rsa私钥解密获取aes加密key,然后使用aes解密密文,获取密码
     * 5. 结合了rsa非对称加密的安全性,以及aes加密的快速
     *
     * @param requestKey 请求唯一编号
     * @return aes公钥å
     */
    @Override
    public String encryptPublicKey(String requestKey) {
        RSA rsa = new RSA();
        // 私钥
        String privateKey = Base64.getEncoder().encodeToString(rsa.getPrivateKey().getEncoded());
        // 公钥
        String publicKey = Base64.getEncoder().encodeToString(rsa.getPublicKey().getEncoded());
        Optional
                .ofNullable(cacheManager.getCache("rsa:key"))
                .ifPresent(cache -> cache.put(requestKey, privateKey));
        return publicKey;
    }

    @Override
    public String decryptPassword(PasswordDecryptForm decryptForm) {
        return Optional
                .ofNullable(cacheManager.getCache("rsa:key"))
                // 先获取rsa 私钥
                .map(cache -> cache.get(decryptForm.getRequestKey()))
                .map(Object::toString)
                // 私钥解密密文,获取aes加密键
                .map(privateKey ->
                        new RSA(null, privateKey)
                                .decryptStr(decryptForm.getCiphertext(), KeyType.PrivateKey)
                )
                // aes解密密码
                .map(aesKey ->
                        new AES(aesKey.getBytes(StandardCharsets.UTF_8))
                                .decryptStr(decryptForm.getPassword())
                )
                .orElse(null);
    }
}
