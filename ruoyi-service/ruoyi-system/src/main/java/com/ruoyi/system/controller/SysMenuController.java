package com.ruoyi.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.common.annotation.LoginUser;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 菜单权限 
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("menu")
public class SysMenuController extends BaseController
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
     * 查询菜单权限
     */
    @GetMapping("user")
    public List<SysMenu> user(@LoginUser SysUser sysUser)
    {
        return sysMenuService.selectMenusByUser(sysUser);
    }
	
	/**
	 * 查询菜单权限列表
	 */
    @GetMapping("list")
    public R list(SysMenu sysMenu)
	{
        return result(sysMenuService.selectMenuList(sysMenu));
	}
	
	
	/**
	 * 新增保存菜单权限
	 */
	@PostMapping("save")
    public R addSave(SysMenu sysMenu)
	{		
        return toAjax(sysMenuService.insertMenu(sysMenu));
	}

	/**
	 * 修改保存菜单权限
	 */
	@PostMapping("update")
    public R editSave(SysMenu sysMenu)
	{		
        return toAjax(sysMenuService.updateMenu(sysMenu));
	}
	
	/**
	 * 删除菜单权限
	 */
	@GetMapping("remove/{menuId}")
    public R remove(@PathVariable("menuId") Long menuId)
	{		
        return toAjax(sysMenuService.deleteMenuById(menuId));
	}
	
}
