package com.ruoyi.activiti.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 费用报销 biz_purchase
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BizPurchase implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long              id;

    /** 标题 */
    private String            title;

    /** 描述 */
    private String            description;

    /** 金额 */
    private Double            money;

    /** 删除标记 */
    private Boolean           delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              createTime;

    @Transient
    private String            procDefId;

    @Transient
    private String            procName;
}
