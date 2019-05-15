package com.ruoyi.system.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 文件上传
 */
@Table(name = "sys_oss")
public class SysOss implements Serializable
{
    //
    private static final long serialVersionUID = 1356257283938225230L;

    @Id
    private Long              id;

    /** 文件名 */
    private String            fileName;

    /** 文件后缀 */
    private String            fileSuffix;

    /** URL地址 */
    private String            url;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date              createTime;

    /** 上传者 */
    private String            createBy;

    /** 服务商 */
    private Integer           service;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileSuffix()
    {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix)
    {
        this.fileSuffix = fileSuffix;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Integer getService()
    {
        return service;
    }

    public void setService(Integer service)
    {
        this.service = service;
    }
    
    
}
