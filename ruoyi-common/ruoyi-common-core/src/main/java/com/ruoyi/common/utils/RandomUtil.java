package com.ruoyi.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil
{
    private static char[] chars  = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'};

    private static char[] nubers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    /**
     * 生成随机字符串，包含数字和字母
     * @param length 长度
     * @return
     * @author zmr
     */
    public static String randomStr(int length)
    {
        return RandomStringUtils.random(length, chars);
    }

    /**
     * 生成随机字符串，只包含数字
     * @param length 长度
     * @return
     * @author zmr
     */
    public static String randomInt(int length)
    {
        return RandomStringUtils.random(length, nubers);
    }
}
