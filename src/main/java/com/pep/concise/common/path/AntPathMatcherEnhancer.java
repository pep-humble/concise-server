package com.pep.concise.common.path;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Ant路径匹配器增强
 *
 * @author gang.liu
 */
public class AntPathMatcherEnhancer {

    /**
     * 默认实例
     */
    private static final AntPathMatcherEnhancer DEFAULT = new AntPathMatcherEnhancer();

    /**
     * 区分大小写的匹配器
     */
    private final AntPathMatcher caseSensitiveMatcher;

    /**
     * 不区分大小写的匹配器
     */
    private final AntPathMatcher caseIgnoreMatcher;

    /**
     * 构造函数
     *
     * @param trimTokens    是否去空格
     * @param cachePatterns 是否缓存表达式
     */
    public AntPathMatcherEnhancer(boolean trimTokens, boolean cachePatterns) {
        this.caseSensitiveMatcher = new AntPathMatcher();
        this.caseSensitiveMatcher.setTrimTokens(trimTokens);
        this.caseSensitiveMatcher.setCaseSensitive(true);
        this.caseSensitiveMatcher.setCachePatterns(cachePatterns);
        this.caseIgnoreMatcher = new AntPathMatcher();
        this.caseIgnoreMatcher.setTrimTokens(trimTokens);
        this.caseIgnoreMatcher.setCaseSensitive(false);
        this.caseIgnoreMatcher.setCachePatterns(cachePatterns);
    }

    /**
     * 构造函数
     */
    public AntPathMatcherEnhancer() {
        this(true, false);
    }

    /**
     * 获取默认实例
     *
     * @return 默认实例
     */
    public static AntPathMatcherEnhancer getDefault() {
        return DEFAULT;
    }

    /**
     * 匹配
     *
     * @param httpMethod  请求方法
     * @param servletPath 请求路径
     * @param matchers    匹配器
     * @return 是否匹配
     */
    public boolean match(String httpMethod, String servletPath, Collection<AntPathRequestMatcher> matchers) {
        if (CollectionUtils.isEmpty(matchers)) {
            return false;
        }
        return matchers
                .stream()
                .anyMatch(matcher -> this.match(httpMethod, servletPath, matcher));
    }

    /**
     * 是否匹配
     *
     * @param httpMethod  请求方法
     * @param servletPath 请求路径
     * @param matcher     匹配器
     * @return 是否匹配
     */
    public boolean match(String httpMethod, String servletPath, AntPathRequestMatcher matcher) {
        if (Objects.isNull(matcher)) {
            return false;
        }
        // 先匹配请求方法
        String matchMethod = Optional
                .ofNullable(matcher.getHttpMethod())
                .map(Enum::name)
                .map(String::trim)
                .map(String::toUpperCase)
                .orElse(null);
        if (Objects.nonNull(matchMethod)) {
            // 匹配器设置了方法，代表需要验证
            String method = Optional
                    .ofNullable(httpMethod)
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .orElse(null);
            if (ObjectUtils.notEqual(matchMethod, method)) {
                return false;
            }
        }
        // 匹配路径
        return this.match(matcher.getPattern(), servletPath, matcher.getCaseSensitive());
    }

    /**
     * 是否匹配
     *
     * @param pattern       路径表达式
     * @param path          路径
     * @param caseSensitive 是否区分大小写
     * @return 是否匹配
     */
    public boolean match(String pattern, String path, Boolean caseSensitive) {
        return this.match(pattern, path, BooleanUtils.isTrue(caseSensitive));
    }

    /**
     * 是否匹配
     *
     * @param pattern       路径表达式
     * @param path          路径
     * @param caseSensitive 是否区分大小写
     * @return 是否匹配
     */
    public boolean match(String pattern, String path, boolean caseSensitive) {
        return (caseSensitive ? this.caseSensitiveMatcher : this.caseIgnoreMatcher).match(pattern, path);
    }

}
