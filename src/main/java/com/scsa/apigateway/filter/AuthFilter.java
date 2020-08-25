//package com.scsa.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.scsa.apigateway.constant.RedisConstant;
//import com.scsa.apigateway.util.CookieUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
///**
// * 权限拦截（买家和卖家权限）
// * @Author: SCSA
// * @Date: 2020/8/25 17:06
// */
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public String filterType() {
//        return PRE_TYPE; //做参数校验，使用pre前置过滤器
//    }
//
//    @Override
//    public int filterOrder() {
//        return PRE_DECORATION_FILTER_ORDER - 1; //过滤器位于pre内的顺序,放到PRE_DECORATION_FILTER_ORDER前面
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true; //是否需要经过过滤器
//    }
//
//    @Override
//    public Object run() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        /**
//         * /order/create 买家访问（cookie里有openid）
//         * /order/finish 卖家访问（cookie里有token，并对应redis中的值）
//         * /product/list 都可访问
//         */
//        if ("/order/order/create".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, "openid");
//            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        if ("/order/order/finish".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, "token");
//            if (cookie == null
//                    || StringUtils.isEmpty(cookie.getValue())
//                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        return null;
//    }
//}
