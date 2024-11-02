package com.pep.concise.business.platform.setting.web.controller;

import com.pep.concise.business.platform.setting.service.SettingService;
import com.pep.concise.business.platform.setting.web.vo.SettingResult;
import com.pep.concise.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统设置接口
 *
 * @author gang.liu
 */
@RestController
@RequestMapping("/system/setting")
@RequiredArgsConstructor
public class SettingController {

    /**
     * 设置项服务
     */
    private final SettingService settingService;

    /**
     * 根据设置项编码获取设置项详情
     *
     * @param settingCode 设置项编码
     * @return 设置项详情
     */
    @GetMapping("/{settingCode}")
    public ResponseResult<SettingResult> getSettingOptional(@PathVariable String settingCode) {
        return new ResponseResult<SettingResult>().setCode(HttpStatus.OK.value())
                .setData(settingService.getSettingOptional(settingCode))
                .setMsg("成功");
    }
}
