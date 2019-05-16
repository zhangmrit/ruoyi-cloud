package com.ruoyi.system.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.feign.factory.SysOssClientFallbackFactory;

/**
 * 文件上传 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-16
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysOssClientFallbackFactory.class)
public interface ISysOssClient
{
    String PREFIX = "system/sysOss";

    @GetMapping(PREFIX + "get/{id}")
    public SysOss selectSysOssById(@PathVariable("id") Long id);

    @RequestMapping(PREFIX + "list")
    public List<SysOss> selectSysOssList(@RequestParam(name = "sysOss") SysOss sysOss,@RequestParam(name = "page") PageDomain page);

    @PostMapping(PREFIX + "save")
    public int insertSysOss(SysOss sysOss);

    @PostMapping(PREFIX + "update")
    public int updateSysOss(SysOss sysOss);

    @RequestMapping(PREFIX + "remove")
    public int deleteSysOssByIds(String ids);
}
