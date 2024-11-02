package com.pep.concise.business.flat;

import com.pep.concise.common.vo.ResponseResult;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单测试工具
 *
 * @author gang.liu
 */
@Validated
@RestController
@RequestMapping("/simple")
public class SimpleController {

    /**
     * 应用名称
     */
    @Value("${spring.application.name}")
    public String appName;

    /**
     * ping接口
     *
     * @return hello, flat-server!
     */
    @GetMapping("/ping")
    public String ping() {
        return String.join("", "hello, ", appName, "!");
    }

    /**
     * ping接口
     *
     * @return hello, flat-server!
     */
    @GetMapping("/ping/response")
    public ResponseEntity<String> pingResponse() {
        return new ResponseEntity<>(ping(), HttpStatus.OK);
    }

    /**
     * ping接口
     *
     * @return hello, flat-server!
     */
    @GetMapping("/ping/result")
    public ResponseResult<String> pingResponseResult() {
        return ResponseResult.success(ping());
    }

    /**
     * ping接口
     *
     * @return hello, flat-server!
     */
    @GetMapping("/ping/input/{param}")
    public String pingInput(@PathVariable @Length(min = 3, max = 5) String param) {
        return String.join(" ", "hello", param);
    }
}
