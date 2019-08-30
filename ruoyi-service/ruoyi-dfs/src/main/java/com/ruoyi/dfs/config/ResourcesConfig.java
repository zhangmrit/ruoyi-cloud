package com.ruoyi.dfs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruoyi.common.constant.Constants;

/**
 * <p>File：ResourcesConfig.java</p>
 * <p>Title: 静态资源映射</p>
 * <p>Description:生产使用为了安全,不建议通过这种方式映射,推荐使用nginx配置</p>
 * <p>Copyright: Copyright (c) 2019 2019年8月30日 上午11:03:21</p>
 * <p>Company: zmrit.com</p>
 * @author zmr
 * @version 1.0
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    @Autowired
    private DfsConfig dfsConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
                .addResourceLocations("file:" + dfsConfig.getPath() + "/");
    }
}