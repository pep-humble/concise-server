package com.pep.concise.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pep.concise.common.enums.ResultErrorStatus;
import com.pep.concise.common.vo.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * 身份验证入口点,前后端分离,直接响应401
 *
 * @author gang.liu
 */
public class AuthenticationResponseEntryPoint implements AuthenticationEntryPoint {

    /**
     * 序列化器
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 前后端分离,直接响应错误码
        response.setStatus(ResultErrorStatus.UN_AUTHORIZED.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ResponseResult<Object> responseResult = ResponseResult.failure(ResultErrorStatus.UN_AUTHORIZED);
        objectMapper.writeValue(response.getOutputStream(), responseResult);
        response.flushBuffer();
    }
}
