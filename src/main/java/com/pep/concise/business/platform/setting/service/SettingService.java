package com.pep.concise.business.platform.setting.service;

import com.pep.concise.business.platform.setting.web.form.SettingForm;
import com.pep.concise.business.platform.setting.web.vo.SettingResult;

/**
 * 设置项目服务
 *
 * @author gang.liu
 */
public interface SettingService {

    /**
     * 根据设置项目编码获取设置项结果
     *
     * @param settingCode 设置项目编码
     * @return 设置项结果
     */
    SettingResult getSettingOptional(String settingCode);

    /**
     * 维护设置项
     *
     * @param form 设置项表单
     * @param <S>  设置项表单类型
     */
    <S> void createSettingOptional(SettingForm<S> form);

}
