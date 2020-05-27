/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.ruoyi.captcha.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("ajCaptchaContainer")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Container implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        if (Container.applicationContext == null)
        {
            Container.applicationContext = applicationContext;
        }
    }

    // 获取applicationContext
    private static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz)
    {
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> tClass)
    {
        return getApplicationContext().getBeansOfType(tClass);
    }
}
