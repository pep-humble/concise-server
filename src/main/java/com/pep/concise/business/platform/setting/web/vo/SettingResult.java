package com.pep.concise.business.platform.setting.web.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置项结果
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class SettingResult {

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
    @JsonProperty(value = "settingJson")
    private String setting;
}
