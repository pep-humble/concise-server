package com.pep.concise.common.http;

import com.pep.concise.common.vo.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 不属于HttpEntity的返回值并且不属于ResponseResult的返回值,包装成ResponseResult,用来适配各种前端项目
 *
 * @author gang.liu
 */
@Component
@ControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> returnClazz = returnType.getParameterType();
        return !HttpEntity.class.isAssignableFrom(returnClazz) && !ResponseResult.class.isAssignableFrom(returnClazz);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        return ResponseResult.success(body);
    }
}
