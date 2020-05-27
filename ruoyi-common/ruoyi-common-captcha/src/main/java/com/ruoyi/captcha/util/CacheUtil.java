/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.ruoyi.captcha.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheUtil {

    private static final Map<String, Object> CACHE_MAP = new ConcurrentHashMap<String, Object>();

    public static void set(String key, String value){

    }

    public static void set(String key, String value, long expiresInSeconds){
        CACHE_MAP.put(key, value);
        CACHE_MAP.put(key + "_HoldTime", System.currentTimeMillis() + expiresInSeconds*1000);//缓存失效时间
    }

    public static void delete(String key){
        CACHE_MAP.remove(key);
        CACHE_MAP.remove(key + "_HoldTime");
    }

    public static boolean exists(String key){
        Long cacheHoldTime = (Long) CACHE_MAP.get(key + "_HoldTime");
        if (cacheHoldTime == null || cacheHoldTime == 0L) {
            return false;
        }
        if (cacheHoldTime < System.currentTimeMillis()) {
            delete(key);
            return false;
        }
        return true;
    }


    public static String get(String key){
        if (exists(key)) {
            return (String)CACHE_MAP.get(key);
        }
        return null;
    }
}
