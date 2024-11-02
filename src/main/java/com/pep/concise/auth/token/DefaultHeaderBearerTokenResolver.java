package com.pep.concise.auth.token;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从请求头中获取authorization的内容
 *
 * @author gang.liu
 */
public class DefaultHeaderBearerTokenResolver implements TokenResolver {

    /**
     * Authorization的合法的表达式
     */
    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$", Pattern.CASE_INSENSITIVE);

    @Override
    public String resolve(HttpServletRequest request, HttpServletResponse response) {
        String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;
        String authorization = request.getHeader(bearerTokenHeaderName);
        if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
            return null;
        }
        Matcher matcher = authorizationPattern.matcher(authorization);
        if (!matcher.matches()) {
            // 畸形的token
            return null;

        }
        return matcher.group("token");
    }
}
