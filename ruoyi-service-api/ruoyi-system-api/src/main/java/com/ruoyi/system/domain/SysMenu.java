package com.ruoyi.system.domain;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单权限表 sys_menu
 * 
 * @author ruoyi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long              menuId;

    /** 菜单名称 */
    private String            menuName;

    /** 父菜单名称 */
    private String            parentName;

    /** 父菜单ID */
    private Long              parentId;

    /** 打开方式 (_blank新窗口) */
    private String            target;

    /** 显示顺序 */
    private String            orderNum;

    /** 类型:0目录,1菜单,2按钮 */
    private String            menuType;

    /** 菜单URL */
    private String            menuKey;

    /** 组件 */
    private String            component;

    /** 菜单状态:0显示,1隐藏 */
    private String            visible;

    /** 权限字符串 */
    private String            perms;

    /** 菜单图标 */
    private String            icon;

    /** 链接地址 */
    private String            path;

    /** 重定向地址 */
    private String            redirect;

    /** 隐藏子菜单 */
    private Boolean           hiddenChildren;

    /** 隐藏 PageHeader 组件中的页面带的 面包屑和页面标题栏 */
    private Boolean           hiddenHeader;

    /** 子菜单 */
    private List<SysMenu>     children         = new ArrayList<SysMenu>();
}
