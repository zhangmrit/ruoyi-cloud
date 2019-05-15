package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;

/**
 * 文件上传
 */
@RestController
@RequestMapping("system/oss")
public class SysOssClient extends BaseController
{
    @Autowired
    private ISysOssService sysOssService;

    /**
     * 列表
     */
    @RequestMapping("list")
    public List<SysOss> list(SysOss sysOss, PageDomain page)
    {
        startPage(page);
        return sysOssService.getList(sysOss);
    }

    /**
     * 保存
     */
    @RequestMapping("save")
    public int upload(SysOss sysOss) throws Exception
    {
        return sysOssService.save(sysOss);
    }

    /**
     * 修改
     */
    @GetMapping("get/{ossId}")
    @ResponseBody
    public SysOss findById(@PathVariable("ossId") Long ossId)
    {
        return sysOssService.findById(ossId);
    }

    /**
     * 修改
     */
    @PostMapping("update")
    public int update(SysOss sysOss)
    {
        return sysOssService.update(sysOss);
    }

    /**
     * 删除
     */
    @RequestMapping("remove")
    public int delete(String ids)
    {
        return sysOssService.deleteByIds(ids);
    }
}
