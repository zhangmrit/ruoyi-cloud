package com.ruoyi.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>File：RedisEvict.java</p>
 * <p>Title: redis删除注解</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年12月6日 下午4:33:31</p>
 * <p>Company:  </p>
 * @author zmr
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisEvict
{
    String key();

    String fieldKey();
}