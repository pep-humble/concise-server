package com.pep.concise.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pep.concise.common.enums.ResultErrorStatus;
import com.pep.concise.common.vo.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 失败直接响应结果
 *
 * @author gang.liu
 */
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 序列化器
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        // 前后端分离,直接响应错误码
        response.setStatus(ResultErrorStatus.UN_AUTHORIZED.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ResponseResult<Object> responseResult = ResponseResult.failure(exception.getMessage(), ResultErrorStatus.UN_AUTHORIZED);
        objectMapper.writeValue(response.getOutputStream(), responseResult);
        response.flushBuffer();
    }
}
