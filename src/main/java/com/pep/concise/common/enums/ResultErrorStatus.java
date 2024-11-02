package com.pep.concise.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 系统内部常用的状态码
 *
 * @author gang.liu
 */
@Getter
@AllArgsConstructor
public enum ResultErrorStatus {

    /**
     * 没有登录
     */
    UN_AUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "令牌不存在或者失效,重新登录"),

    /**
     * 权限不足
     */
    UN_PERMISSION(HttpStatus.FORBIDDEN.value(), "权限不足,不允许访问"),

    /**
     * 访问资源不存在
     */
    NO_RESOURCES(HttpStatus.NOT_FOUND.value(), "访问资源不存在"),

    /**
     * 参数异常
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "请求参数异常"),
    ;

    /**
     * 响应码
     */
    private final int code;

    /**
     * 提示消息
     */
    private final String message;

}
