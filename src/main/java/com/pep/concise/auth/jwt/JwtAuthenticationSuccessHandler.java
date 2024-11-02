package com.pep.concise.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.pep.concise.auth.token.TokenManager;
import com.pep.concise.auth.token.TokenResult;
import com.pep.concise.common.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功后,返回双token
 *
 * @author gang.liu
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 序列化器
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 令牌生成器
     */
    private final TokenManager tokenGenerator;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            TokenResult generator = tokenGenerator.generator(authentication);
            ResponseResult<TokenResult> tokenResultResponseResult = ResponseResult.success(generator);
            response.getWriter().write(objectMapper.writeValueAsString(tokenResultResponseResult));
        } catch (JOSEException e) {
            log.error("", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
        response.flushBuffer();
    }
}
