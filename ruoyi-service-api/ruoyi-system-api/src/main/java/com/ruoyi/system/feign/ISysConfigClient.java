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
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.feign.factory.SysConfigClientFallbackFactory;

/**
 * 参数配置 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysConfigClientFallbackFactory.class) 
public interface ISysConfigClient
{
	String PREFIX = "sys/sysConfig";
	
	@GetMapping(PREFIX + "get/{configId}")	
	public SysConfig selectSysConfigById(@PathVariable("configId") Integer configId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysConfig> selectSysConfigList(@RequestParam(name = "sysConfig") SysConfig sysConfig,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysConfig(SysConfig sysConfig);
	
	@PostMapping(PREFIX + "update")
	public int updateSysConfig(SysConfig sysConfig);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysConfigByIds(String ids);
	
}
