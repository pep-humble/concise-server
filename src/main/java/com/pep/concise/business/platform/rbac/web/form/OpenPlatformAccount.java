package com.pep.concise.business.platform.rbac.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 开放平台账号
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class OpenPlatformAccount {

    /**
     * 平台类型
     */
    @NotBlank
    private String openPlatform;

    /**
     * 开放平台账号
     */
    @NotBlank
    private String openId;
}
