package com.ruoyi.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.captcha.model.common.ResponseModel;
import com.ruoyi.captcha.model.vo.CaptchaVO;
import com.ruoyi.captcha.service.CaptchaService;

/**
 * Created by raodeming on 2019/12/25.
 */
@RestController("ajCaptchaController")
@RequestMapping("/captcha")
public class CaptchaController
{
    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/get")
    public ResponseModel get(@RequestBody CaptchaVO captchaVO)
    {
        return captchaService.get(captchaVO);
    }

    @PostMapping("/check")
    public ResponseModel check(@RequestBody CaptchaVO captchaVO)
    {
        return captchaService.check(captchaVO);
    }

    @PostMapping("/verify")
    public ResponseModel verify(@RequestBody CaptchaVO captchaVO)
    {
        return captchaService.verification(captchaVO.getCaptchaVerification());
    }
}