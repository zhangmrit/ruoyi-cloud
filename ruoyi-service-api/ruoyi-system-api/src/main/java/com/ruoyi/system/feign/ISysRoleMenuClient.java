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
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.feign.factory.SysRoleMenuClientFallbackFactory;

/**
 * 角色和菜单关联 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysRoleMenuClientFallbackFactory.class) 
public interface ISysRoleMenuClient
{
	String PREFIX = "sys/sysRoleMenu";
	
	@GetMapping(PREFIX + "get/{roleId}")	
	public SysRoleMenu selectSysRoleMenuById(@PathVariable("roleId") Integer roleId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysRoleMenu> selectSysRoleMenuList(@RequestParam(name = "sysRoleMenu") SysRoleMenu sysRoleMenu,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysRoleMenu(SysRoleMenu sysRoleMenu);
	
	@PostMapping(PREFIX + "update")
	public int updateSysRoleMenu(SysRoleMenu sysRoleMenu);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysRoleMenuByIds(String ids);
	
}
