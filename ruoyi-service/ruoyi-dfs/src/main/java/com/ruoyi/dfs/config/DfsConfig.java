package com.ruoyi.dfs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <p>File：DfsConfig.java</p>
 * <p>Title: 分布式文件配置</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年8月29日 下午5:27:02</p>
 * <p>Company: yinrunnet.com </p>
 * @author zmr
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "dfs")
public class DfsConfig
{
    /** 路径*/
    private String path;

    //生产环境建议用nginx绑定域名映射
    /** 域名*/
    private String domain;
}