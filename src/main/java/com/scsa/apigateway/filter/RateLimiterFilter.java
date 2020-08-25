package com.scsa.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 限流
 * @Author: SCSA
 * @Date: 2020/8/25 16:12
 */
@Component
public class RateLimiterFilter extends ZuulFilter {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100); //令牌桶算法组件,每秒100个

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1; //优先级拉到最高
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!RATE_LIMITER.tryAcquire()) {
            //没有拿到令牌
            throw new RuntimeException();
        }
        return null;
    }
}
