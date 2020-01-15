package com.ruoyi.activiti.vo;

import java.util.List;

import lombok.Data;

@Data
public class ProcessNodeVo
{
    // 节点id
    private String nodeId;

    // 节点名
    private String name;

    // 节点类型 0开始 1用户任务 2结束
    private Integer type;

    // 关联角色
    private List<Long> roleIds;

    // 关联用户
    private List<Long> userIds;

    // 关联部门
    private List<Long> deptIds;

    // 选操作人的部门负责人
    private Boolean deptHeader = false;
}