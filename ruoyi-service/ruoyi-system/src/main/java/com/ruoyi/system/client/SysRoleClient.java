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
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 角色 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysRole")
public class SysRoleClient extends BaseController
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
	@PostMapping("list")
	public List<SysRole> list(SysRole sysRole, PageDomain page)
	{
		startPage(page);
        return sysRoleService.selectRoleList(sysRole);
	}
	
	
	/**
	 * 新增保存角色
	 */
	@PostMapping("save")
	public int addSave(SysRole sysRole)
	{		
		return sysRoleService.insertRole(sysRole);
	}

	/**
	 * 修改保存角色
	 */
	@PostMapping("update")
	public int editSave(SysRole sysRole)
	{		
		return sysRoleService.updateRole(sysRole);
	}
	
	/**
	 * 删除角色
	 * @throws Exception 
	 */
	@PostMapping("remove")
	public int remove(String ids) throws Exception
	{		
		return sysRoleService.deleteRoleByIds(ids);
	}
	
}
