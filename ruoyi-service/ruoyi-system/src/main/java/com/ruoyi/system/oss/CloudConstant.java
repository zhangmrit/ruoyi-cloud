package com.ruoyi.system.oss;

public class CloudConstant
{
    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "sys.oss.cloudStorage";

    /**
       * 云服务商
       */
    public enum CloudService
    {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);
        private int value;

        CloudService(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }
}