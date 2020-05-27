/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.ruoyi.captcha.service;

/**
 * @Title: 验证码缓存接口
 * @author lide1202@hotmail.com
 * @date 2018-08-21
 */
public interface CaptchaCacheService {

	void set(String key, String value, long expiresInSeconds);

	boolean exists(String key);

	void delete(String key);

	String get(String key);

}
