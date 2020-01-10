package com.ruoyi.activiti.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizBusiness implements Serializable
{
    //
    private static final long serialVersionUID = -7562556845627977390L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long              id;

    private String            title;

    private Long              userId;

    private String            tableId;

    private String            procDefId;

    private String            procDefKey;

    private String            procInstId;

    // 流程名称
    private String            procName;

    // 当前任务节点名称
    private String            currentTask;

    private String            applyer;

    private Integer           status;

    private Integer           result;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              applyTime;

    private Boolean           delFlag;
}
