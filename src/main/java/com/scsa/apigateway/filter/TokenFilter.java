package com.scsa.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限校验,必须携带token
 * @Author: SCSA
 * @Date: 2020/8/25 15:47
 */
@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE; //做参数校验，使用pre前置过滤器
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1; //过滤器位于pre内的顺序,放到PRE_DECORATION_FILTER_ORDER前面
    }

    @Override
    public boolean shouldFilter() {
        return true; //是否需要经过过滤器
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //从url参数中获取，也可从cookie，header里获取
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            requestContext.setSendZuulResponse(false); //表示不通过
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
