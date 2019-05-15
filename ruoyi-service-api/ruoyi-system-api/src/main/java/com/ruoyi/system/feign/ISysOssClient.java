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

@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysOssClientFallbackFactory.class)
public interface ISysOssClient
{
    String API_PREFIX = "system/oss/";

    @RequestMapping(API_PREFIX + "list")
    List<SysOss> getList(@RequestParam("sysOss") SysOss sysOss, @RequestParam("page") PageDomain page);

    @RequestMapping(API_PREFIX + "save")
    int save(SysOss ossEntity);

    @GetMapping(API_PREFIX + "get/{ossId}")
    SysOss findById(@PathVariable("ossId") Long ossId);

    @PostMapping(API_PREFIX + "update")
    int update(SysOss sysOss);

    @RequestMapping(API_PREFIX + "remove")
    int deleteByIds(String ids);
}
