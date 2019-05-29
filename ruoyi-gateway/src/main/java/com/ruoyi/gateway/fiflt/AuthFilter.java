package com.ruoyi.gateway.fiflt;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ruoyi.common.constant.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019-05-29 14:32</p>
 * <p>Company: </p>
 *
 * @version 1.0
 * @author: zmr
 */
@Slf4j
@Component
public class AuthFilter extends ZuulFilter
{
    @Autowired
    private StringRedisTemplate   redisTemplate;

    // 排除过滤的 uri 地址
    private static final String[] whiteList    = {"/api/auth/login", "/user/user/register"};

    private static final String   REGISTER_URI = "/user/user/register";

    private static final String   TOKEN        = "token";

    private final static String   ACCESS_TOKEN = "access_token_";

    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder()
    {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter()
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();
        log.info("uri:{}", uri);
        // 注册和登录接口不拦截，其他接口都要拦截校验 token
        for (String whiteUrl : whiteList)
        {
            if (uri.equals(whiteUrl)
                    || (whiteUrl.endsWith("/*") && uri.indexOf(whiteUrl.substring(0, whiteUrl.length() - 1)) != -1))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException
    {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 从header中获取token
        String token = request.getHeader(TOKEN);
        // 如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token))
        {
            token = request.getParameter(TOKEN);
        }
        // token为空
        if (StringUtils.isBlank(token))
        {
            setUnauthorizedResponse(requestContext, "token can't null or empty string");
        }
        if (StringUtils.isNotBlank(token))
        {
            // 查询token信息
            log.info(ACCESS_TOKEN + TOKEN);
            String accessToken = redisTemplate.opsForValue().get(ACCESS_TOKEN + token);
            log.info(accessToken);
            if (accessToken == null)
            {
                setUnauthorizedResponse(requestContext, "token verify error");
            }
            else
            {
                // 设置userId到request里，后续根据userId，获取用户信息
                request.setAttribute(Constants.USER_KEY, accessToken);
            }
        }
        return null;
    }

    /** * 设置 401 无权限状态 */
    private void setUnauthorizedResponse(RequestContext requestContext, String msg)
    {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        Map map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", msg);
        requestContext.setResponseBody(JSON.toJSONString(map));
    }
}
