package com.ruoyi.activiti.vo;

import org.activiti.engine.runtime.Execution;

import java.util.Date;

public class RuExecution
{
    private String  id;

    private Integer rev;

    private String  procInstId;

    private String  businessKey;

    private String  parentId;

    private String  procDefId;

    private String  superExec;

    private String  rootProcInstId;

    private String  actId;

    private Byte    isActive;

    private Byte    isConcurrent;

    private Byte    isScope;

    private Byte    isEventScope;

    private Byte    isMiRoot;

    private Integer suspensionState;

    private Integer cachedEntState;

    private String  tenantId;

    private String  name;

    private Date    startTime;

    private String  startUserId;

    private Date    lockTime;

    private Byte    isCountEnabled;

    private Integer evtSubscrCount;

    private Integer taskCount;

    private Integer jobCount;

    private Integer timerJobCount;

    private Integer suspJobCount;

    private Integer deadletterJobCount;

    private Integer varCount;

    private Integer idLinkCount;

    public RuExecution(Execution execution)
    {
        setId(execution.getId());
        setParentId(execution.getParentId());
        setProcInstId(execution.getProcessInstanceId());
        setActId(execution.getActivityId());
        setName(execution.getName());
        setTenantId(execution.getTenantId());
    }

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

    public String getProcInstId()
    {
        return procInstId;
    }

    public void setProcInstId(String procInstId)
    {
        this.procInstId = procInstId == null ? null : procInstId.trim();
    }

    public String getBusinessKey()
    {
        return businessKey;
    }

    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey == null ? null : businessKey.trim();
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getProcDefId()
    {
        return procDefId;
    }

    public void setProcDefId(String procDefId)
    {
        this.procDefId = procDefId == null ? null : procDefId.trim();
    }

    public String getSuperExec()
    {
        return superExec;
    }

    public void setSuperExec(String superExec)
    {
        this.superExec = superExec == null ? null : superExec.trim();
    }

    public String getRootProcInstId()
    {
        return rootProcInstId;
    }

    public void setRootProcInstId(String rootProcInstId)
    {
        this.rootProcInstId = rootProcInstId == null ? null : rootProcInstId.trim();
    }

    public String getActId()
    {
        return actId;
    }

    public void setActId(String actId)
    {
        this.actId = actId == null ? null : actId.trim();
    }

    public Byte getIsActive()
    {
        return isActive;
    }

    public void setIsActive(Byte isActive)
    {
        this.isActive = isActive;
    }

    public Byte getIsConcurrent()
    {
        return isConcurrent;
    }

    public void setIsConcurrent(Byte isConcurrent)
    {
        this.isConcurrent = isConcurrent;
    }

    public Byte getIsScope()
    {
        return isScope;
    }

    public void setIsScope(Byte isScope)
    {
        this.isScope = isScope;
    }

    public Byte getIsEventScope()
    {
        return isEventScope;
    }

    public void setIsEventScope(Byte isEventScope)
    {
        this.isEventScope = isEventScope;
    }

    public Byte getIsMiRoot()
    {
        return isMiRoot;
    }

    public void setIsMiRoot(Byte isMiRoot)
    {
        this.isMiRoot = isMiRoot;
    }

    public Integer getSuspensionState()
    {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState)
    {
        this.suspensionState = suspensionState;
    }

    public Integer getCachedEntState()
    {
        return cachedEntState;
    }

    public void setCachedEntState(Integer cachedEntState)
    {
        this.cachedEntState = cachedEntState;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public String getStartUserId()
    {
        return startUserId;
    }

    public void setStartUserId(String startUserId)
    {
        this.startUserId = startUserId == null ? null : startUserId.trim();
    }

    public Date getLockTime()
    {
        return lockTime;
    }

    public void setLockTime(Date lockTime)
    {
        this.lockTime = lockTime;
    }

    public Byte getIsCountEnabled()
    {
        return isCountEnabled;
    }

    public void setIsCountEnabled(Byte isCountEnabled)
    {
        this.isCountEnabled = isCountEnabled;
    }

    public Integer getEvtSubscrCount()
    {
        return evtSubscrCount;
    }

    public void setEvtSubscrCount(Integer evtSubscrCount)
    {
        this.evtSubscrCount = evtSubscrCount;
    }

    public Integer getTaskCount()
    {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount)
    {
        this.taskCount = taskCount;
    }

    public Integer getJobCount()
    {
        return jobCount;
    }

    public void setJobCount(Integer jobCount)
    {
        this.jobCount = jobCount;
    }

    public Integer getTimerJobCount()
    {
        return timerJobCount;
    }

    public void setTimerJobCount(Integer timerJobCount)
    {
        this.timerJobCount = timerJobCount;
    }

    public Integer getSuspJobCount()
    {
        return suspJobCount;
    }

    public void setSuspJobCount(Integer suspJobCount)
    {
        this.suspJobCount = suspJobCount;
    }

    public Integer getDeadletterJobCount()
    {
        return deadletterJobCount;
    }

    public void setDeadletterJobCount(Integer deadletterJobCount)
    {
        this.deadletterJobCount = deadletterJobCount;
    }

    public Integer getVarCount()
    {
        return varCount;
    }

    public void setVarCount(Integer varCount)
    {
        this.varCount = varCount;
    }

    public Integer getIdLinkCount()
    {
        return idLinkCount;
    }

    public void setIdLinkCount(Integer idLinkCount)
    {
        this.idLinkCount = idLinkCount;
    }
}