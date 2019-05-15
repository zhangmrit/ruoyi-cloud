package com.ruoyi.framework.web.exception.user;

/**
 * OSS信息异常类
 * 
 * @author zmr
 */
public class OssException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public OssException(String msg)
    {
        super(msg);
    }
}
