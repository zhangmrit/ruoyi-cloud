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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 部门 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("dept")
public class SysDeptController extends BaseController
{
    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询部门
     */
    @GetMapping("get/{deptId}")
    public SysDept get(@PathVariable("deptId") Long deptId)
    {
        return sysDeptService.selectDeptById(deptId);
    }

    /**
     * 查询部门列表
     */
    @GetMapping("list")
    public R list(SysDept sysDept, PageDomain page)
    {
        startPage(page);
        return result(sysDeptService.selectDeptList(sysDept));
    }

    /**
     * 新增保存部门
     */
    @PostMapping("save")
    public R addSave(SysDept sysDept)
    {
        return toAjax(sysDeptService.insertDept(sysDept));
    }

    /**
     * 修改保存部门
     */
    @PostMapping("update")
    public R editSave(SysDept sysDept)
    {
        return toAjax(sysDeptService.updateDept(sysDept));
    }

    /**
     * 删除部门
     */
    @PostMapping("remove/{deptId}")
    public R remove(@PathVariable("deptId") Long deptId)
    {
        return toAjax(sysDeptService.deleteDeptById(deptId));
    }
}
