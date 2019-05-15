package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.feign.ISysOssClient;

/**
 * 文件上传
 */
@RestController
@RequestMapping("client/oss")
public class SysOssClientController extends BaseController
{
    @Autowired
    private ISysOssClient sysOssClient;

    @RequestMapping("list")
    public TableDataInfo list(SysOss sysOss)
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        List<SysOss> list = sysOssClient.getList(sysOss,pageDomain);
        return getDataTable(list);
    }
    
    @GetMapping("get/{ossId}")
    public SysOss get(@PathVariable("ossId") Long ossId, Model model)
    {
        return  sysOssClient.findById(ossId);
    }
}
