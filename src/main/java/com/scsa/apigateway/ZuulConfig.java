package com.scsa.apigateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: SCSA
 * @Date: 2020/8/24 21:39
 */
@Component
public class ZuulConfig {

    /**
     * zuul配置动态注入(git)
     * @return
     */
    @ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

}
