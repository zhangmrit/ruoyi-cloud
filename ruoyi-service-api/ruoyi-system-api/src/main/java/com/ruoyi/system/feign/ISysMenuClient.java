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
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.feign.factory.SysMenuClientFallbackFactory;

/**
 * 菜单权限 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysMenuClientFallbackFactory.class) 
public interface ISysMenuClient
{
	String PREFIX = "sys/sysMenu";
	
	@GetMapping(PREFIX + "get/{menuId}")	
	public SysMenu selectSysMenuById(@PathVariable("menuId") Integer menuId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysMenu> selectSysMenuList(@RequestParam(name = "sysMenu") SysMenu sysMenu,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysMenu(SysMenu sysMenu);
	
	@PostMapping(PREFIX + "update")
	public int updateSysMenu(SysMenu sysMenu);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysMenuByIds(String ids);
	
}
