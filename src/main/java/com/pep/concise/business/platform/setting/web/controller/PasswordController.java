package com.pep.concise.business.platform.setting.web.controller;

import com.pep.concise.business.platform.setting.service.PasswordService;
import com.pep.concise.business.platform.setting.web.vo.PasswordEncryptKeyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 密码管理接口
 *
 * @author gang.liu
 */
@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {

    /**
     * 密码服务
     */
    private final PasswordService passwordService;

    /**
     * 获取密码加密公钥
     *
     * @param requestKey 请求唯一编号
     * @return 加密公钥
     */
    @GetMapping("/publicKey/{requestKey}")
    public PasswordEncryptKeyResult encryptPublicKey(@PathVariable String requestKey) {
        return new PasswordEncryptKeyResult()
                .setPublicKey(passwordService.encryptPublicKey(requestKey));
    }
}
