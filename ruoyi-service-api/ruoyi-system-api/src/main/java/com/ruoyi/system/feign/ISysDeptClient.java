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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.feign.factory.SysDeptClientFallbackFactory;

/**
 * 部门 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysDeptClientFallbackFactory.class) 
public interface ISysDeptClient
{
	String PREFIX = "sys/sysDept";
	
	@GetMapping(PREFIX + "get/{deptId}")	
	public SysDept selectSysDeptById(@PathVariable("deptId") Integer deptId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysDept> selectSysDeptList(@RequestParam(name = "sysDept") SysDept sysDept,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysDept(SysDept sysDept);
	
	@PostMapping(PREFIX + "update")
	public int updateSysDept(SysDept sysDept);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysDeptByIds(String ids);
	
}
