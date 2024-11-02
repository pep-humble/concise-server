package com.pep.concise.auth.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 令牌抽取器
 *
 * @author gang.liu
 */
public interface TokenResolver {

    /**
     * 抽取令牌
     *
     * @param request  请求体
     * @param response 响应体
     * @return 令牌
     */
    String resolve(HttpServletRequest request, HttpServletResponse response);
}
