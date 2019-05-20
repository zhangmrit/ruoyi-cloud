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
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.feign.factory.SysUserOnlineClientFallbackFactory;

/**
 * 在线用户记录 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysUserOnlineClientFallbackFactory.class) 
public interface ISysUserOnlineClient
{
	String PREFIX = "sys/sysUserOnline";
	
	@GetMapping(PREFIX + "get/{sessionId}")	
	public SysUserOnline selectSysUserOnlineById(@PathVariable("sessionId") String sessionId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysUserOnline> selectSysUserOnlineList(@RequestParam(name = "sysUserOnline") SysUserOnline sysUserOnline,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysUserOnline(SysUserOnline sysUserOnline);
	
	@PostMapping(PREFIX + "update")
	public int updateSysUserOnline(SysUserOnline sysUserOnline);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysUserOnlineByIds(String ids);
	
}
