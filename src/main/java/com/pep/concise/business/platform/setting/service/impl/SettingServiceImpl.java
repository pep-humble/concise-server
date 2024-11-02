package com.pep.concise.business.platform.setting.service.impl;

import com.pep.concise.business.platform.setting.service.SettingService;
import com.pep.concise.business.platform.setting.web.form.SettingForm;
import com.pep.concise.business.platform.setting.web.vo.SettingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 设置项目服务
 *
 * @author gang.liu
 */
@Slf4j
@Service
public class SettingServiceImpl implements SettingService {

    @Override
    public SettingResult getSettingOptional(String settingCode) {
        if ("GrayModelSetting".equals(settingCode)) {
            return new SettingResult()
                    .setSetting("{\"enable\":false}")
                    .setSettingName("灰色模式")
                    .setSettingComponentName("GrayModelSetting");
        }
        if ("SignInSetting".equals(settingCode)) {
            return new SettingResult()
                    .setSetting("{\"enable\":true,\"deptIds\":[\"1810226204790657025\",\"1842128409600917505\",\"1842128590274756609\",\"1842128734768529409\",\"1842128938393600002\"],\"defaultDeptId\":\"\",\"postIds\":[\"1842188089392041986\",\"1842129449343713282\"],\"roleIds\":[\"1842149851067514881\"]}")
                    .setSettingName("自动注册")
                    .setSettingComponentName("SignInSetting");
        }
        return null;
    }

    @Override
    public <S> void createSettingOptional(SettingForm<S> form) {

    }
}
