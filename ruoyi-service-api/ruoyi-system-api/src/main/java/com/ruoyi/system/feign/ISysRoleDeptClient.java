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
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.feign.factory.SysRoleDeptClientFallbackFactory;

/**
 * 角色和部门关联 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysRoleDeptClientFallbackFactory.class) 
public interface ISysRoleDeptClient
{
	String PREFIX = "sys/sysRoleDept";
	
	@GetMapping(PREFIX + "get/{roleId}")	
	public SysRoleDept selectSysRoleDeptById(@PathVariable("roleId") Integer roleId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysRoleDept> selectSysRoleDeptList(@RequestParam(name = "sysRoleDept") SysRoleDept sysRoleDept,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysRoleDept(SysRoleDept sysRoleDept);
	
	@PostMapping(PREFIX + "update")
	public int updateSysRoleDept(SysRoleDept sysRoleDept);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysRoleDeptByIds(String ids);
	
}
