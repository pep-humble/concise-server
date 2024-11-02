package com.pep.concise.business.platform.setting.web.controller;

import com.pep.concise.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码接口
 *
 * @author gang.liu
 */
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    @GetMapping("/enable")
    public ResponseResult<Boolean> enable() {
        return ResponseResult.success(Boolean.FALSE);
    }
}
