package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;

/**
 * 通知公告 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("notice")
public class SysNoticeClient extends BaseController
{
	
	@Autowired
	private ISysNoticeService sysNoticeService;
	
	/**
	 * 查询通知公告
	 */
	@GetMapping("get/{noticeId}")
	public SysNotice get(@PathVariable("noticeId") Long noticeId)
	{
		return sysNoticeService.selectNoticeById(noticeId);
		
	}
	
	/**
	 * 查询通知公告列表
	 */
	@PostMapping("list")
	public List<SysNotice> list(SysNotice sysNotice, PageDomain page)
	{
		startPage();
        return sysNoticeService.selectNoticeList(sysNotice);
	}
	
	
	/**
	 * 新增保存通知公告
	 */
	@PostMapping("save")
	public int addSave(SysNotice sysNotice)
	{		
		return sysNoticeService.insertNotice(sysNotice);
	}

	/**
	 * 修改保存通知公告
	 */
	@PostMapping("update")
	public int editSave(SysNotice sysNotice)
	{		
		return sysNoticeService.updateNotice(sysNotice);
	}
	
	/**
	 * 删除通知公告
	 */
	@PostMapping("remove")
	public int remove(String ids)
	{		
		return sysNoticeService.deleteNoticeByIds(ids);
	}
	
}
