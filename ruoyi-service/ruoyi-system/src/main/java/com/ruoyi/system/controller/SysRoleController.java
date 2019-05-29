package com.ruoyi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 角色 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController
{
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	/**
	 * 查询角色
	 */
	@GetMapping("get/{roleId}")
	public SysRole get(@PathVariable("roleId") Long roleId)
	{
		return sysRoleService.selectRoleById(roleId);
		
	}
	
	/**
	 * 查询角色列表
	 */
	@GetMapping("list")
	public R list(SysRole sysRole, PageDomain page)
	{
		startPage(page);
        return result(sysRoleService.selectRoleList(sysRole));
	}
	@GetMapping("all")
	public R all()
	{
	    return R.ok().put("rows", sysRoleService.selectRoleAll());
	}
	
	
	/**
	 * 新增保存角色
	 */
	@PostMapping("save")
	public R addSave(SysRole sysRole)
	{		
		return toAjax(sysRoleService.insertRole(sysRole));
	}

	/**
	 * 修改保存角色
	 */
	@PostMapping("update")
	public R editSave(SysRole sysRole)
	{		
		return toAjax(sysRoleService.updateRole(sysRole));
	}
	
	/**
	 * 删除角色
	 * @throws Exception 
	 */
	@PostMapping("remove")
	public R remove(String ids) throws Exception
	{		
		return toAjax(sysRoleService.deleteRoleByIds(ids));
	}
	
}
