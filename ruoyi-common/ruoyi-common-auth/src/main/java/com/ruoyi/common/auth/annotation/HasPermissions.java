package com.ruoyi.common.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>File：HasPermissions.java</p>
 * <p>Title: 权限注解</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年6月4日 上午10:43:54</p>
 * <p>Company: </p>
 * @author zmr
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermissions
{
    String value();
}