package com.ruoyi.activiti.vo;

import org.activiti.engine.repository.ProcessDefinition;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReProcdef
{
    private String  id;

    private Integer rev;

    private String  category;

    private String  name;

    private String  key;

    private Integer version;

    private String  deploymentId;

    private String  resourceName;

    private String  dgrmResourceName;

    private String  description;

    private Byte    hasStartFormKey;

    private Byte    hasGraphicalNotation;

    private Integer suspensionState;

    private String  tenantId;

    private String  engineVersion;

    public ReProcdef(ProcessDefinition processDefinition)
    {
        setId(processDefinition.getId());
        setCategory(processDefinition.getCategory());
        setName(processDefinition.getName());
        setKey(processDefinition.getKey());
        setVersion(processDefinition.getVersion());
        setDeploymentId(processDefinition.getDeploymentId());
        setResourceName(processDefinition.getResourceName());
        setDgrmResourceName(processDefinition.getDiagramResourceName());
        setDescription(processDefinition.getDescription());
    }

    public ReProcdef(String id, Integer rev, String category, String name, String key, Integer version,
            String deploymentId, String resourceName, String dgrmResourceName, String description, Byte hasStartFormKey,
            Byte hasGraphicalNotation, Integer suspensionState, String tenantId, String engineVersion)
    {
        this.id = id;
        this.rev = rev;
        this.category = category;
        this.name = name;
        this.key = key;
        this.version = version;
        this.deploymentId = deploymentId;
        this.resourceName = resourceName;
        this.dgrmResourceName = dgrmResourceName;
        this.description = description;
        this.hasStartFormKey = hasStartFormKey;
        this.hasGraphicalNotation = hasGraphicalNotation;
        this.suspensionState = suspensionState;
        this.tenantId = tenantId;
        this.engineVersion = engineVersion;
    }
}