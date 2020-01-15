package com.ruoyi.activiti.consts;

import java.awt.Color;

public interface ActivitiConstant
{
    /**
     * 状态 处理中
     */
    Integer STATUS_DEALING   = 1;

    /**
     * 状态 处理结束
     */
    Integer STATUS_FINISH    = 2;

    /**
     * 状态 已撤回
     */
    Integer STATUS_CANCELED  = 3;

    /**
     * 状态 已中止
     */
    Integer STATUS_SUSPEND   = 4;

    /**
     * 结果 处理中
     */
    Integer RESULT_DEALING   = 1;

    /**
     * 结果 通过
     */
    Integer RESULT_PASS      = 2;

    /**
     * 结果 驳回
     */
    Integer RESULT_FAIL      = 3;

    /**
     * 结果 撤回
     */
    Integer RESULT_CANCELED  = 4;

    /**
     * 结果 删除
     */
    Integer RESULT_DELETED   = 5;

    /**
     * 结果 中止
     */
    Integer RESULT_SUSPEND   = 6;

    /**
     * 删除理由前缀
     */
    String  DELETE_PRE       = "deleted-";

    /**
     * 取消理由前缀
     */
    String  CANCEL_PRE       = "canceled-";

    /**
     * 中断理由前缀
     */
    String  SUSPEND_PRE      = "suspend-";

    String  REASON_COMPLETED = "completed";

    String  REASON_DELETED   = "deleted";

    String  END_TASK_NAME    = "审批结束";

    /**
     * 节点类型 开始节点
     */
    Integer NODE_TYPE_START  = 0;

    /**
     * 节点类型 用户任务
     */
    Integer NODE_TYPE_TASK   = 1;

    /**
     * 节点类型 结束
     */
    Integer NODE_TYPE_END    = 2;

    /**
     * 节点类型 排他网关
     */
    Integer NODE_TYPE_EG     = 3;

    /**E
     * 节点关联类型 角色
     */
    Integer NODE_ROLE        = 1;

    /**
     * 节点关联类型 部门
     */
    Integer NODE_DEPARTMENT  = 2;

    /**
     * 节点关联类型 用户
     */
    Integer NODE_USER        = 3;

    /**
     * 节点关联类型 操作人的部门负责人
     */
    Integer NODE_DEP_HEADER  = 4;

    /** 定义生成流程图时的边距(像素) **/
    Integer PROCESS_PADDING  = 5;

    /** 动态流程图颜色定义 **/
    Color   COLOR_NORMAL     = new Color(0, 205, 0);

    Color   COLOR_CURRENT    = new Color(255, 0, 0);
}
