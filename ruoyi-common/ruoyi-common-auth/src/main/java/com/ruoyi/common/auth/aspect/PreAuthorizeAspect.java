package com.ruoyi.common.auth.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.common.auth.annotation.HasPermissions;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.system.feign.RemoteMenuService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PreAuthorizeAspect
{
    @Autowired
    private RemoteMenuService sysMenuClient;

    @Around("@annotation(com.ruoyi.common.auth.annotation.HasPermissions)")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        HasPermissions annotation = method.getAnnotation(HasPermissions.class);
        if (annotation == null)
        {
            return point.proceed();
        }
        String authority = new StringBuilder(annotation.value()).toString();
        if (has(authority))
        {
            return point.proceed();
        }
        else
        {
            return R.error(401, "权限不足");
        }
    }

    private boolean has(String authority)
    {
        // 用超管帐号方便测试，拥有所有权限
        HttpServletRequest request = ServletUtils.getRequest();
        Long userid = Long.valueOf(request.getHeader(Constants.USER_KEY));
        log.debug("userid:{}", userid);
        if (null != userid)
        {
            if (userid == 1l)
            {
                return true;
            }
            return sysMenuClient.selectPermsByUserId(userid).stream().anyMatch(a -> authority.equals(a));
        }
        return false;
    }
}