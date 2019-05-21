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
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.service.ISysDictTypeService;

/**
 * 字典类型 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysDictType")
public class SysDictTypeClient extends BaseController
{
	
	@Autowired
	private ISysDictTypeService sysDictTypeService;
	
	/**
	 * 查询字典类型
	 */
	@GetMapping("get/{dictId}")
	public SysDictType get(@PathVariable("dictId") Long dictId)
	{
		return sysDictTypeService.selectDictTypeById(dictId);
		
	}
	
	/**
	 * 查询字典类型列表
	 */
	@PostMapping("list")
	public List<SysDictType> list(SysDictType sysDictType, PageDomain page)
	{
		startPage(page);
        return sysDictTypeService.selectDictTypeList(sysDictType);
	}
	
	
	/**
	 * 新增保存字典类型
	 */
	@PostMapping("save")
	public int addSave(SysDictType sysDictType)
	{		
		return sysDictTypeService.insertDictType(sysDictType);
	}

	/**
	 * 修改保存字典类型
	 */
	@PostMapping("update")
	public int editSave(SysDictType sysDictType)
	{		
		return sysDictTypeService.updateDictType(sysDictType);
	}
	
	/**
	 * 删除字典类型
	 * @throws Exception 
	 */
	@PostMapping("remove")
	public int remove(String ids) throws Exception
	{		
		return sysDictTypeService.deleteDictTypeByIds(ids);
	}
	
}
