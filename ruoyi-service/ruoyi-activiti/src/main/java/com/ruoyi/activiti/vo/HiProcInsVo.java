package com.ruoyi.activiti.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HiProcInsVo
{
    private String  id;

    private String  procDefId;

    private String  procDefKey;

    private String  procInstId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    startTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    endTime;

    private Long    duration;

    private String  deleteReason;

    private String  procName;

    private String  businessKey;

    private String  tableId;

    private String  title;

    private String  applyer;

    private Integer result;

    private Integer status;

    private Long    userId;
}
