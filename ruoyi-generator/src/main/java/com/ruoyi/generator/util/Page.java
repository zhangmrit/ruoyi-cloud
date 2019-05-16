package com.ruoyi.generator.util;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>File：Page.java</p>
 * <p>Title: 分页返回对象</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年11月2日 下午6:12:24</p>
 * <p>Company: yinrunnet.com </p>
 * @author 张孟如
 * @version 1.0
 */
@Data
public class Page implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int               code             = 0;

    /** 总记录数 */
    private long              total;

    /** 列表数据 */
    private List<?>           rows;

    // 每页的记录数
    private int               pageSize         = 10;

    // 当前是第几页
    private int               page             = 1;
}
