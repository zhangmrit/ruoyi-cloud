package com.ruoyi.common.log.publish;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.log.event.SysLogininforEvent;
import com.ruoyi.common.utils.AddressUtils;
import com.ruoyi.common.utils.IpUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.spring.SpringContextHolder;
import com.ruoyi.system.domain.SysLogininfor;

import eu.bitwalker.useragentutils.UserAgent;

public class PublishFactory
{
    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     */
    public static void recordLogininfor(final String username, final String status, final String message,
            final Object ... args)
    {
        HttpServletRequest request = ServletUtils.getRequest();
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(request);
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setLoginName(username);
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        logininfor.setMsg(message);
        // 日志状态
        if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status))
        {
            logininfor.setStatus(Constants.SUCCESS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.FAIL);
        }
        // 发布事件
        SpringContextHolder.publishEvent(new SysLogininforEvent(logininfor));
    }
}
