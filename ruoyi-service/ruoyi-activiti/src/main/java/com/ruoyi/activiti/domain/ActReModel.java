package com.ruoyi.activiti.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流程设计模型部署对象 ACT_RE_MODEL
 * 
 * @author ruoyi
 * @date 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActReModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String            id;

    /** 乐观锁 */
    private Long              rev;

    /** 名称 */
    @Excel(name = "名称")
    private String            name;

    /** 模型标识 */
    @Excel(name = "模型标识")
    private String            key;

    /** 分类 */
    @Excel(name = "分类")
    private String            category;

    /** 最新修改时间 */
    private Date              lastUpdateTime;

    /** 版本 */
    @Excel(name = "版本")
    private Long              version;

    /** 以json格式保存流程定义的信息 */
    private String            metaInfo;

    /** 部署ID */
    @Excel(name = "部署ID")
    private String            deploymentId;

    /** 编辑源值ID */
    private String            editorSourceValueId;

    /** 编辑源额外值ID */
    private String            editorSourceExtraValueId;

    /** 租户 */
    private String            tenantId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              createTime;
}
