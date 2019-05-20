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
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.feign.factory.SysOperLogClientFallbackFactory;

/**
 * 操作日志记录 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysOperLogClientFallbackFactory.class) 
public interface ISysOperLogClient
{
	String PREFIX = "sys/sysOperLog";
	
	@GetMapping(PREFIX + "get/{operId}")	
	public SysOperLog selectSysOperLogById(@PathVariable("operId") Integer operId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysOperLog> selectSysOperLogList(@RequestParam(name = "sysOperLog") SysOperLog sysOperLog,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysOperLog(SysOperLog sysOperLog);
	
	@PostMapping(PREFIX + "update")
	public int updateSysOperLog(SysOperLog sysOperLog);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysOperLogByIds(String ids);
	
}
