package com.ruoyi.system.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;

/**
 * 文件上传 提供者
 * 
 * @author zmr
 * @date 2019-05-16
 */
@RestController
@RequestMapping("/system/sysOss")
public class SysOssClient extends BaseController
{
	
	@Autowired
	private ISysOssService sysOssService;
	
	/**
	 * 查询文件上传
	 */
	@GetMapping("get/{id}")
	public SysOss get(@PathVariable("id") Long id)
	{
		return sysOssService.selectSysOssById(id);
		
	}
	
	/**
	 * 查询文件上传列表
	 */
	@PostMapping("list")
	public List<SysOss> list(SysOss sysOss, PageDomain page)
	{
		startPage(page);
        return sysOssService.selectSysOssList(sysOss);
	}
	
	
	/**
	 * 新增保存文件上传
	 */
	@PostMapping("save")
	public int addSave(SysOss sysOss)
	{		
		return sysOssService.insertSysOss(sysOss);
	}

	/**
	 * 修改保存文件上传
	 */
	@PostMapping("update")
	public int editSave(SysOss sysOss)
	{		
		return sysOssService.updateSysOss(sysOss);
	}
	
	/**
	 * 删除文件上传
	 */
	@PostMapping("remove")
	public int remove(String ids)
	{		
		return sysOssService.deleteSysOssByIds(ids);
	}
	
}
