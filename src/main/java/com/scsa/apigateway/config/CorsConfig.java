package com.scsa.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * @Author: SCSA
 * @Date: 2020/8/25 19:58
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); //支持cookie跨域
        config.setAllowedOrigins(Arrays.asList("*")); //原始域
        config.setAllowedHeaders(Arrays.asList("*")); //允许头
        config.setAllowedMethods(Arrays.asList("*")); //允许方法
        config.setMaxAge(300l); //缓存事件

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
