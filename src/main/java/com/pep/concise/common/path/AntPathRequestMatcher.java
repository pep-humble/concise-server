package com.pep.concise.common.path;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 请求匹配器
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class AntPathRequestMatcher {

    /**
     * 请求方法
     */
    private HttpMethod httpMethod;

    /**
     * 地址表达式
     */
    private String pattern;

    /**
     * 是否大小写敏感
     */
    private Boolean caseSensitive;
}
