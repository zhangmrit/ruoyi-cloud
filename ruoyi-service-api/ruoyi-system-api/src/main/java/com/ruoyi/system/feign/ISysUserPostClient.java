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
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.feign.factory.SysUserPostClientFallbackFactory;

/**
 * 用户与岗位关联 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysUserPostClientFallbackFactory.class) 
public interface ISysUserPostClient
{
	String PREFIX = "sys/sysUserPost";
	
	@GetMapping(PREFIX + "get/{userId}")	
	public SysUserPost selectSysUserPostById(@PathVariable("userId") Integer userId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysUserPost> selectSysUserPostList(@RequestParam(name = "sysUserPost") SysUserPost sysUserPost,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysUserPost(SysUserPost sysUserPost);
	
	@PostMapping(PREFIX + "update")
	public int updateSysUserPost(SysUserPost sysUserPost);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysUserPostByIds(String ids);
	
}
