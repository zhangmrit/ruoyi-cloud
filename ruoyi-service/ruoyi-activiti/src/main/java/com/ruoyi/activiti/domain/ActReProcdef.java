/*
 * @(#)ActReProcdef.java 2020年1月3日 下午6:19:47
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>File：ActReProcdef.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月3日 下午6:19:47</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ActReProcdef
{
    /** id */
    @Id
    @Column(name = "ID_")
    private String  id;

    /** 流程名称（该编号就是流程文件process元素的name属性值） */
    @Column(name = "NAME_")
    private String  name;

    /** 流程编号(该编号就是流程文件process元素的id属性值) */
    @Column(name = "KEY_")
    private String  key;

    /** 版本号 */
    @Column(name = "REV_")
    private String  rev;

    /** 流程命名空间（该编号就是流程文件targetNamespace的属性值） */
    @Column(name = "CATEGORY_")
    private String  category;

    /** 流程版本号（由程序控制，新增即为1，修改后依次加1来完成的） */
    @Column(name = "VERSION_")
    private String  version;

    /** 部署编号 部署表ID*/
    @Column(name = "DEPLOYMENT_ID_")
    private String  deploymentId;

    /** 资源文件名称 流程bpmn文件名称*/
    @Column(name = "RESOURCE_NAME_")
    private String  resourceName;

    /** 图片资源文件名称 png流程图片名称*/
    @Column(name = "DGRM_RESOURCE_NAME_")
    private String  dgrmResourceName;

    /** 描述信息 */
    @Column(name = "DESCRIPTION_")
    private String  description;

    /** 是否从key启动 start节点是否存在formKey 0否  1是 */
    @Column(name = "HAS_START_FORM_KEY_")
    private Boolean hasStartFormKey;

    /** 是否挂起 1激活 2挂起 */
    @Column(name = "SUSPENSION_STATE_")
    private Integer  suspensionState;
}
