package com.pep.concise.business.platform.setting.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置项表单
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class SettingForm<S> {

    /**
     * 设置项编码
     */
    private String settingComponentName;

    /**
     * 设置项名称
     */
    private String settingName;

    /**
     * 设置项内容
     */
    private S setting;
}
