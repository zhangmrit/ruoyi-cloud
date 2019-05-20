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
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.feign.factory.SysDictDataClientFallbackFactory;

/**
 * 字典数据 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysDictDataClientFallbackFactory.class) 
public interface ISysDictDataClient
{
	String PREFIX = "sys/sysDictData";
	
	@GetMapping(PREFIX + "get/{dictCode}")	
	public SysDictData selectSysDictDataById(@PathVariable("dictCode") Integer dictCode);
	
	@RequestMapping(PREFIX + "list")
	public List<SysDictData> selectSysDictDataList(@RequestParam(name = "sysDictData") SysDictData sysDictData,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysDictData(SysDictData sysDictData);
	
	@PostMapping(PREFIX + "update")
	public int updateSysDictData(SysDictData sysDictData);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysDictDataByIds(String ids);
	
}
