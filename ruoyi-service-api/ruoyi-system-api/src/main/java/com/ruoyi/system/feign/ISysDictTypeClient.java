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
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.feign.factory.SysDictTypeClientFallbackFactory;

/**
 * 字典类型 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysDictTypeClientFallbackFactory.class) 
public interface ISysDictTypeClient
{
	String PREFIX = "sys/sysDictType";
	
	@GetMapping(PREFIX + "get/{dictId}")	
	public SysDictType selectSysDictTypeById(@PathVariable("dictId") Integer dictId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysDictType> selectSysDictTypeList(@RequestParam(name = "sysDictType") SysDictType sysDictType,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysDictType(SysDictType sysDictType);
	
	@PostMapping(PREFIX + "update")
	public int updateSysDictType(SysDictType sysDictType);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysDictTypeByIds(String ids);
	
}
