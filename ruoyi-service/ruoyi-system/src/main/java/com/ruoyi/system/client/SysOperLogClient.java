package com.ruoyi.system.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 操作日志记录 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysOperLog")
public class SysOperLogClient extends BaseController
{
    @Autowired
    private ISysOperLogService sysOperLogService;

    /**
     * 查询操作日志记录
     */
    @GetMapping("get/{operId}")
    public SysOperLog get(@PathVariable("operId") Long operId)
    {
        return sysOperLogService.selectOperLogById(operId);
    }

    /**
     * 查询操作日志记录列表
     */
    @PostMapping("list")
    public List<SysOperLog> list(SysOperLog sysOperLog, PageDomain page)
    {
        startPage(page);
        return sysOperLogService.selectOperLogList(sysOperLog);
    }

    /**
     * 新增保存操作日志记录
     */
    @PostMapping("save")
    public void addSave(SysOperLog sysOperLog)
    {
        sysOperLogService.insertOperlog(sysOperLog);
    }

    /**
     * 删除操作日志记录
     */
    @PostMapping("remove")
    public int remove(String ids)
    {
        return sysOperLogService.deleteOperLogByIds(ids);
    }
}
