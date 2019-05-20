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
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.feign.factory.SysPostClientFallbackFactory;

/**
 * 岗位 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysPostClientFallbackFactory.class) 
public interface ISysPostClient
{
	String PREFIX = "sys/sysPost";
	
	@GetMapping(PREFIX + "get/{postId}")	
	public SysPost selectSysPostById(@PathVariable("postId") Integer postId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysPost> selectSysPostList(@RequestParam(name = "sysPost") SysPost sysPost,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysPost(SysPost sysPost);
	
	@PostMapping(PREFIX + "update")
	public int updateSysPost(SysPost sysPost);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysPostByIds(String ids);
	
}
