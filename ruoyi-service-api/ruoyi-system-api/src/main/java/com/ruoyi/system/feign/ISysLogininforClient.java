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
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.feign.factory.SysLogininforClientFallbackFactory;

/**
 * 系统访问记录 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysLogininforClientFallbackFactory.class) 
public interface ISysLogininforClient
{
	String PREFIX = "sys/sysLogininfor";
	
	@GetMapping(PREFIX + "get/{infoId}")	
	public SysLogininfor selectSysLogininforById(@PathVariable("infoId") Integer infoId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysLogininfor> selectSysLogininforList(@RequestParam(name = "sysLogininfor") SysLogininfor sysLogininfor,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysLogininfor(SysLogininfor sysLogininfor);
	
	@PostMapping(PREFIX + "update")
	public int updateSysLogininfor(SysLogininfor sysLogininfor);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysLogininforByIds(String ids);
	
}
