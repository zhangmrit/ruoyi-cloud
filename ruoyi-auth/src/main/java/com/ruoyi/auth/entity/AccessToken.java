package com.ruoyi.auth.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "api_token")
public class AccessToken implements Serializable
{
    //
    private static final long serialVersionUID = -7958022740493612407L;

    /**
     * 用户ID
     */
    @Id
    private Long              userId;

    private String            token;

    /**
     * 过期时间
     */
    private Date              expireTime;
}