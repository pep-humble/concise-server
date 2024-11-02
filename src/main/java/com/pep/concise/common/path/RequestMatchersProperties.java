package com.pep.concise.common.path;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * 请求匹配器属性
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class RequestMatchersProperties {

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 请求方法
     */
    private Set<HttpMethod> httpMethods;

    /**
     * 是否大小写敏感
     */
    private Boolean caseSensitive;
}
