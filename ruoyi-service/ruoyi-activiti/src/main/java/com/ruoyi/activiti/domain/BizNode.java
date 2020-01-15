/*
 * @(#)BizNode.java 2020年1月14日 上午10:59:12
 * Copyright 2020 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.activiti.domain;

import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * <p>File：BizNode.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月14日 上午10:59:12</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
@Data
@Accessors(chain=true)
public class BizNode
{
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long    id;

    /** 节点ID*/
    private String  nodeId;

    /** 类型 1：角色 2：部门负责人 3：用户 4：所属部门负责人*/
    private Integer type;

    /** 类型对应负责人的值*/
    private Long    auditor;
}
