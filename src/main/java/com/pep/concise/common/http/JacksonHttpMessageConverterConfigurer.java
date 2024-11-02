package com.pep.concise.common.http;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * jackson 消息转换器配置
 *
 * @author gang.liu
 */
@Configuration
@ConditionalOnClass(Servlet.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JacksonHttpMessageConverterConfigurer implements WebMvcConfigurer {

    /**
     * 扩展消息转换器
     *
     * @param converters 要扩展的消息转换器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> collect = converters
                .stream()
                .filter(convert -> convert.getClass().isAssignableFrom(MappingJackson2HttpMessageConverter.class))
                .collect(Collectors.toList());
        converters.removeAll(collect);
        // 将jackson相关的消息转换器移到最前面
        converters.addAll(0, collect);
    }
}
