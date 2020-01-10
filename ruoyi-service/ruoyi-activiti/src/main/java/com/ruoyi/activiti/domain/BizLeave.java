package com.ruoyi.activiti.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 请假对象 act_leave
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BizLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long              id;

    /** 标题 */
    @Excel(name = "标题")
    private String            title;

    /** 描述 */
    @Excel(name = "描述")
    private String            description;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date              startDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date              endDate;

    /** 时长 */
    @Excel(name = "时长")
    private Double            duration;

    /** 请假类型 */
    @Excel(name = "请假类型")
    private Integer           type;

    /** 删除标记 */
    private Boolean           delFlag;

    @Transient
    private String            procDefId;

    @Transient
    private String            procName;
}
