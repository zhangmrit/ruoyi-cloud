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
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.factory.SysUserClientFallbackFactory;

/**
 * 用户 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysUserClientFallbackFactory.class) 
public interface ISysUserClient
{
	String PREFIX = "sys/sysUser";
	
	@GetMapping(PREFIX + "get/{userId}")	
	public SysUser selectSysUserById(@PathVariable("userId") Integer userId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysUser> selectSysUserList(@RequestParam(name = "sysUser") SysUser sysUser,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysUser(SysUser sysUser);
	
	@PostMapping(PREFIX + "update")
	public int updateSysUser(SysUser sysUser);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysUserByIds(String ids);
	
}
