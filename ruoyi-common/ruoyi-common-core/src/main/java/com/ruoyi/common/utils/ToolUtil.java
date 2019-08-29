package com.ruoyi.common.utils;

import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: 高频方法集合类</p>
 * <p>Copyright: Copyright (c) 2019-08-19 14:51</p>
 * <p>Company: </p>
 * @version 1.0
 * @author: zmr
 */
public class ToolUtil
{
    /**
     * 获取临时目录
     * @author zmr
     */
    public static String getTempPath()
    {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取当前项目工作目录
     * @return
     * @author zmr
     */
    public static String getUserDir()
    {
        return System.getProperty("user.dir");
    }

    /**
     * 获取临时下载目录
     * @return
     * @author zmr
     */
    public static String getDownloadPath()
    {
        return getTempPath() + File.separator + "download" + File.separator;
    }
}
