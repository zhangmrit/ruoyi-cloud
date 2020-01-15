package com.ruoyi.activiti.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <p>File：HiTaskVo.java</p>
 * <p>Title: 历史任务vo</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月9日 上午11:49:40</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Data
public class HiTaskVo
{
    // 任务编号
    private String  id;

    // 流程定义编号
    private String  procDefId;

    // 流程实例编号
    private String  procInstId;

    // 任务环节名称
    private String  taskName;

    // 任务节点key
    private String  taskDefKey;

    // 处理人
    private Long    auditorId;

    // 开始时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    startTime;

    // 结束时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    endTime;

    // 审批结果
    private Integer result;

    private String  comment;

    private String  routerName;

    // 耗时
    private Long    duration;

    private String  businessKey;

    // 流程名称
    private String  procName;

    // 申请标题
    private String  title;

    private String  procDefKey;

    private String  startUserId;

    private String  applyer;

    private Boolean delFlag;

    // 审批记录编号
    private Long    auditId;
    
    private String  auditor;

    // 删除原因 completed：任务执行 deleted：流程删除
    private String  deleteReason;
}
