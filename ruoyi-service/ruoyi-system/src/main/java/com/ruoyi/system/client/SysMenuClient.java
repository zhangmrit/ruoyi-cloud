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
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 菜单权限 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysMenu")
public class SysMenuClient extends BaseController
{
	
	@Autowired
	private ISysMenuService sysMenuService;
	
	/**
	 * 查询菜单权限
	 */
	@GetMapping("get/{menuId}")
	public SysMenu get(@PathVariable("menuId") Long menuId)
	{
		return sysMenuService.selectMenuById(menuId);
		
	}
	
	/**
	 * 查询菜单权限列表
	 */
	@PostMapping("list")
	public List<SysMenu> list(SysMenu sysMenu, PageDomain page)
	{
		startPage(page);
        return sysMenuService.selectMenuList(sysMenu);
	}
	
	
	/**
	 * 新增保存菜单权限
	 */
	@PostMapping("save")
	public int addSave(SysMenu sysMenu)
	{		
		return sysMenuService.insertMenu(sysMenu);
	}

	/**
	 * 修改保存菜单权限
	 */
	@PostMapping("update")
	public int editSave(SysMenu sysMenu)
	{		
		return sysMenuService.updateMenu(sysMenu);
	}
	
	/**
	 * 删除菜单权限
	 */
	@GetMapping("remove/{menuId}")
	public int remove(@PathVariable("menuId") Long menuId)
	{		
		return sysMenuService.deleteMenuById(menuId);
	}
	
}
