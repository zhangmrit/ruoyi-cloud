/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package com.ruoyi.captcha.service;

import com.ruoyi.captcha.model.common.ResponseModel;
import com.ruoyi.captcha.model.vo.CaptchaVO;

/**
 * @Title: 验证码缓存接口
 * @author lide1202@hotmail.com
 * @date 2020-05-12
 */
public interface CaptchaService {

    /**
     * 获取验证码
     * @param captchaVO
     * @return
     */
    ResponseModel get(CaptchaVO captchaVO);

    /**
     * 核对验证码(前端)
     * @param captchaVO
     * @return
     */
    ResponseModel check(CaptchaVO captchaVO);

    /**
     * 二次校验验证码(后端)
     * @param captchaVO
     * @return
     */
    ResponseModel verification(String verify);


}
