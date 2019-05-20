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
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.feign.factory.SysRoleClientFallbackFactory;

/**
 * 角色 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysRoleClientFallbackFactory.class) 
public interface ISysRoleClient
{
	String PREFIX = "sys/sysRole";
	
	@GetMapping(PREFIX + "get/{roleId}")	
	public SysRole selectSysRoleById(@PathVariable("roleId") Integer roleId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysRole> selectSysRoleList(@RequestParam(name = "sysRole") SysRole sysRole,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysRole(SysRole sysRole);
	
	@PostMapping(PREFIX + "update")
	public int updateSysRole(SysRole sysRole);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysRoleByIds(String ids);
	
}
