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
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.feign.factory.SysNoticeClientFallbackFactory;

/**
 * 通知公告 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = SysNoticeClientFallbackFactory.class) 
public interface ISysNoticeClient
{
	String PREFIX = "sys/sysNotice";
	
	@GetMapping(PREFIX + "get/{noticeId}")	
	public SysNotice selectSysNoticeById(@PathVariable("noticeId") Integer noticeId);
	
	@RequestMapping(PREFIX + "list")
	public List<SysNotice> selectSysNoticeList(@RequestParam(name = "sysNotice") SysNotice sysNotice,@RequestParam(name = "page") PageDomain page);
	
	@PostMapping(PREFIX + "save")
	public int insertSysNotice(SysNotice sysNotice);
	
	@PostMapping(PREFIX + "update")
	public int updateSysNotice(SysNotice sysNotice);
		
	@RequestMapping(PREFIX + "remove")
	public int deleteSysNoticeByIds(String ids);
	
}
