package com.ruoyi.activiti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.activiti.vo.ReProcdef;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;

/**
 * 流程控制接口
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/5 15:04
 */
@RestController
@RequestMapping("prof")
public class ActivitiController extends BaseController
{
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService    runtimeService;

    /**
     * 启动一个流程
     * 
     * @param key
     * @return
     * @author zmr
     */
    @GetMapping("start/{key}")
    public R start(@PathVariable("key") String key)
    {
        runtimeService.startProcessInstanceByKey(key);
        return R.ok();
    }

    @GetMapping("list")
    public R list(PageDomain page)
    {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .listPage(page.getPageSize() * (page.getPageNum() - 1), page.getPageSize());
        long count = repositoryService.createProcessDefinitionQuery().count();
        List<ReProcdef> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions)
        {
            ReProcdef reProcdef = new ReProcdef(processDefinition);
            list.add(reProcdef);
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("rows", list);
        m.put("pageNum", page.getPageNum());
        m.put("total", count);
        return R.ok(m);
    }

    @PostMapping("delete/{id}")
    public R deleteOne(@PathVariable("id") String id)
    {
        // 根据deploymentID删除定义的流程，普通删除
        repositoryService.deleteDeployment(id);
        System.out.println("普通删除--流程定义删除成功");
        return R.ok();
        // 强制删除
        // repositoryService.deleteDeployment(id, true);
        // System.out.println("强制删除--流程定义删除成功");
    }
}
