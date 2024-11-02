package com.pep.concise.common.path;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * ant匹配规则的路径
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class RequestMatcherInfo {

    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 地址表达式
     */
    private List<String> patterns;
}
