package com.pep.concise.common.cors;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MVC 跨域配置
 *
 * @author gang.liu
 */
@Configuration
@ConditionalOnClass(Servlet.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CorsConfigurer {

    /**
     * 跨域配置
     *
     * @return 跨域配置
     */
    @Bean
    @ConfigurationProperties(prefix = "business.cors")
    public Map<String, CorsConfiguration> corsConfigurations() {
        return new LinkedHashMap<>();
    }

    /**
     * 跨域配置导入到web mvc框架
     *
     * @param corsConfigurations 跨域配置
     * @return 跨域web mvc配置
     */
    @Bean
    public WebMvcConfigurer webMvcCorsConfigurer(Map<String, CorsConfiguration> corsConfigurations) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                corsConfigurations
                        .entrySet()
                        .stream()
                        .filter(entry -> Objects.nonNull(entry.getKey()))
                        .filter(entry -> Objects.nonNull(entry.getValue()))
                        .forEach(
                                entry -> {
                                    CorsConfiguration configuration = entry.getValue();
                                    registry
                                            .addMapping(entry.getKey().trim())
                                            .allowCredentials(BooleanUtils.isTrue(configuration.getAllowCredentials()))
                                            .allowedHeaders(StringUtils.toStringArray(configuration.getAllowedHeaders()))
                                            .allowedMethods(StringUtils.toStringArray(configuration.getAllowedMethods()))
                                            .allowedOriginPatterns(StringUtils.toStringArray(configuration.getAllowedOriginPatterns()));
                                }
                        );
            }
        };
    }
}
