package com.pep.concise.auth.jwt;

import com.pep.concise.auth.token.TokenManager;
import com.pep.concise.auth.token.TokenResolver;
import com.pep.concise.common.path.AntPathMatcherEnhancer;
import com.pep.concise.common.path.AntPathRequestMatcher;
import com.pep.concise.common.path.RequestMatcherInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.function.SingletonSupplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 从请求中获取jwt令牌,并且解析
 *
 * @author gang.liu
 */
@Slf4j
public class JwtSecurityContextRepository implements SecurityContextRepository {

    /**
     * 令牌抽取器
     */
    private final TokenResolver tokenResolver;

    /**
     * 令牌解析器
     */
    private final TokenManager tokenManager;

    /**
     * 免鉴权的路径匹配器
     */
    private Collection<AntPathRequestMatcher> permitAllAntPathRequestMatchers;

    /**
     * 构造器
     *
     * @param tokenResolver               令牌解析器
     * @param tokenManager                令牌抽取器
     * @param permitAllRequestMatcherInfo 免鉴权路径
     */
    public JwtSecurityContextRepository(TokenResolver tokenResolver,
                                        TokenManager tokenManager,
                                        List<RequestMatcherInfo> permitAllRequestMatcherInfo) {
        this.tokenManager = tokenManager;
        this.tokenResolver = tokenResolver;
        if (CollectionUtils.isNotEmpty(permitAllRequestMatcherInfo)) {
            this.permitAllAntPathRequestMatchers = permitAllRequestMatcherInfo
                    .stream()
                    .map(requestMatcherInfo -> {
                        HttpMethod method = requestMatcherInfo.getMethod();
                        List<String> patterns = requestMatcherInfo.getPatterns();
                        if (CollectionUtils.isNotEmpty(patterns)) {
                            return patterns.stream()
                                    .map(pattern ->
                                            new AntPathRequestMatcher()
                                                    .setPattern(pattern)
                                                    .setHttpMethod(method)
                                    )
                                    .collect(Collectors.toList());
                        }
                        return null;
                    })
                    .filter(CollectionUtils::isNotEmpty)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 从请求中获取上下文
     *
     * @param request 请求
     * @return 认证上下文
     */
    @Override
    public Supplier<SecurityContext> loadContext(HttpServletRequest request) {
        return SingletonSupplier.of(() -> {
            boolean match = AntPathMatcherEnhancer.getDefault()
                    .match(
                            request.getMethod(),
                            request.getServletPath(),
                            permitAllAntPathRequestMatchers
                    );
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            if (!match) {
                String token = tokenResolver.resolve(request, null);
                if (StringUtils.isNotBlank(token)) {
                    Authentication authentication = tokenManager.parse(token);
                    if (Objects.nonNull(authentication)) {
                        context.setAuthentication(authentication);
                    }
                }
            }
            return context;
        });
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        // 无状态,save context不实现
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }

    /**
     * @deprecated 过期的方法
     */
    @Override
    @Deprecated
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }
}
