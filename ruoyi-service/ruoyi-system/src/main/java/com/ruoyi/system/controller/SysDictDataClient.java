package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;

/**
 * 字典数据 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysDictData")
public class SysDictDataClient extends BaseController
{
	
	@Autowired
	private ISysDictDataService sysDictDataService;
	
	/**
	 * 查询字典数据
	 */
	@GetMapping("get/{dictCode}")
	public SysDictData get(@PathVariable("dictCode") Long dictCode)
	{
		return sysDictDataService.selectDictDataById(dictCode);
		
	}
	
	/**
	 * 查询字典数据列表
	 */
	@PostMapping("list")
	public List<SysDictData> list(SysDictData sysDictData)
	{
		startPage();
        return sysDictDataService.selectDictDataList(sysDictData);
	}
	
	
	/**
	 * 新增保存字典数据
	 */
	@PostMapping("save")
	public int addSave(SysDictData sysDictData)
	{		
		return sysDictDataService.insertDictData(sysDictData);
	}

	/**
	 * 修改保存字典数据
	 */
	@PostMapping("update")
	public int editSave(SysDictData sysDictData)
	{		
		return sysDictDataService.updateDictData(sysDictData);
	}
	
	/**
	 * 删除字典数据
	 */
	@PostMapping("remove")
	public int remove(String ids)
	{		
		return sysDictDataService.deleteDictDataByIds(ids);
	}
	
}
