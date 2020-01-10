package com.ruoyi.activiti.domain;

import java.io.Serializable;
import java.util.Date;

public class ActRuTask implements Serializable
{
    private String            id;

    private Integer           rev;

    private String            executionId;

    private String            procInstId;

    private String            procDefId;

    private String            name;

    private String            parentTaskId;

    private String            description;

    private String            taskDefKey;

    private String            owner;

    private String            assignee;

    private String            delegation;

    private Integer           priority;

    private Date              createTime;

    private Date              dueDate;

    private String            category;

    private Integer           suspensionState;

    private String            tenantId;

    private String            formKey;

    private static final long serialVersionUID = 1L;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRev()
    {
        return rev;
    }

    public void setRev(Integer rev)
    {
        this.rev = rev;
    }

    public String getExecutionId()
    {
        return executionId;
    }

    public void setExecutionId(String executionId)
    {
        this.executionId = executionId == null ? null : executionId.trim();
    }

    public String getProcInstId()
    {
        return procInstId;
    }

    public void setProcInstId(String procInstId)
    {
        this.procInstId = procInstId == null ? null : procInstId.trim();
    }

    public String getProcDefId()
    {
        return procDefId;
    }

    public void setProcDefId(String procDefId)
    {
        this.procDefId = procDefId == null ? null : procDefId.trim();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }

    public String getParentTaskId()
    {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId)
    {
        this.parentTaskId = parentTaskId == null ? null : parentTaskId.trim();
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description == null ? null : description.trim();
    }

    public String getTaskDefKey()
    {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey)
    {
        this.taskDefKey = taskDefKey == null ? null : taskDefKey.trim();
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getAssignee()
    {
        return assignee;
    }

    public void setAssignee(String assignee)
    {
        this.assignee = assignee == null ? null : assignee.trim();
    }

    public String getDelegation()
    {
        return delegation;
    }

    public void setDelegation(String delegation)
    {
        this.delegation = delegation == null ? null : delegation.trim();
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category == null ? null : category.trim();
    }

    public Integer getSuspensionState()
    {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState)
    {
        this.suspensionState = suspensionState;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getFormKey()
    {
        return formKey;
    }

    public void setFormKey(String formKey)
    {
        this.formKey = formKey == null ? null : formKey.trim();
    }
}