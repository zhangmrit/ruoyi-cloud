package com.ruoyi.activiti.vo;

import lombok.Data;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * <p>File：ProcessInsVo.java</p>
 * <p>Title: 流程实例视图</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月6日 下午1:29:16</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Data
public class ProcessInsVo
{
    private String  id;

    private String  name;

    private String  key;

    private Integer version;

    private String  description;

    private String  businessKey;

    private String  tableId;

    private String  parentId;

    private String  procDefId;

    private String  procInstId;

    private String  actId;

    private Boolean isSuspended;

    private String  tenantId;

    private String  deployId;

    private String  currTaskName;

    private String  routeName;

    private String  applyer;

    public ProcessInsVo(ProcessInstance pi)
    {
        this.id = pi.getId();
        this.name = pi.getName();
        this.key = pi.getProcessDefinitionKey();
        this.version = pi.getProcessDefinitionVersion();
        this.description = pi.getDescription();
        this.businessKey = pi.getBusinessKey();
        this.parentId = pi.getParentId();
        this.procDefId = pi.getProcessDefinitionId();
        this.procInstId = pi.getProcessInstanceId();
        this.actId = pi.getActivityId();
        this.isSuspended = pi.isSuspended();
        this.tenantId = pi.getTenantId();
        this.deployId = pi.getDeploymentId();
    }
}
