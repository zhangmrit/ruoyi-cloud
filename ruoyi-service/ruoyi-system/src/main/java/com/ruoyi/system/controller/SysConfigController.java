package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("config")
public class SysConfigController extends BaseController
{
	
	@Autowired
	private ISysConfigService sysConfigService;
	
	/**
	 * 查询参数配置
	 */
	@GetMapping("get/{configId}")
	public SysConfig get(@PathVariable("configId") Long configId)
	{
		return sysConfigService.selectConfigById(configId);
		
	}
	
	/**
	 * 查询参数配置列表
	 */
	@PostMapping("list")
	public List<SysConfig> list(SysConfig sysConfig)
	{
		startPage();
        return sysConfigService.selectConfigList(sysConfig);
	}
	
	
	/**
	 * 新增保存参数配置
	 */
	@PostMapping("save")
	public int addSave(SysConfig sysConfig)
	{		
		return sysConfigService.insertConfig(sysConfig);
	}

	/**
	 * 修改保存参数配置
	 */
	@PostMapping("update")
	public int editSave(SysConfig sysConfig)
	{		
		return sysConfigService.updateConfig(sysConfig);
	}
	
	/**
	 * 删除参数配置
	 */
	@PostMapping("remove")
	public int remove(String ids)
	{		
		return sysConfigService.deleteConfigByIds(ids);
	}
	
}
