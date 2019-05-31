/*
 * @(#)PathInfo.java 2018年11月6日 上午9:27:58
 * Copyright 2018 张孟如, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.generator.domain;

import lombok.Data;

/**
 * <p>File：GenQo.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年11月6日 上午9:27:58</p>
 * <p>Company: yinrunnet.com </p>
 * @author 张孟如
 * @version 1.0
 */
@Data
public class GenQo
{
    // 物理路径
    private Boolean zippack;

    // 物理路径
    private String  projectpath;

    // 包名
    private String  packname;

    // 模块名
    private String  modulename;

    // 表前缀(类名不会包含表前缀)
    private String  tableprefix;

    // 是否自动去除前缀
    private Boolean autoremovepre;

    // mapper.xml路径
    private String  mapperpath;

    // 模版路径
    private String  templatepath;

    // 作者
    private String  author;

    private Boolean pageSwitch;

    private Boolean sqlSwitch;
}
