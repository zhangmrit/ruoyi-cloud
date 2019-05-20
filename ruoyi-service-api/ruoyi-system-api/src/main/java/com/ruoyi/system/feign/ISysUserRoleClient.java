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
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.feign.factory.SysUserRoleClientFallbackFactory;

/**
 * 用户和角色关联 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysUserRoleClientFallbackFactory.class) 
public interface ISysUserRoleClient
{
	String PREFIX = "sys/sysUserRole";
	
	@GetMapping(PREFIX + "get/{userId}")	
	public SysUserRole selectSysUserRoleById(@PathVariable("userId") Integer userId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysUserRole> selectSysUserRoleList(@RequestParam(name = "sysUserRole") SysUserRole sysUserRole,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysUserRole(SysUserRole sysUserRole);
	
	@PostMapping(PREFIX + "update")
	public int updateSysUserRole(SysUserRole sysUserRole);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysUserRoleByIds(String ids);
	
}
