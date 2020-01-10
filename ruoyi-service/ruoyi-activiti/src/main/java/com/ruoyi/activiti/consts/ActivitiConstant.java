package com.ruoyi.activiti.consts;

public interface ActivitiConstant
{
    /**
     * 状态 待提交申请
     */
    Integer STATUS_TO_APPLY  = 0;

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
     * 结果 待提交
     */
    Integer RESULT_TO_SUBMIT = 0;

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
     * 删除理由前缀
     */
    String  DELETE_PRE       = "deleted-";

    /**
     * 取消理由前缀
     */
    String  CANCEL_PRE       = "canceled-";

    /**
     * 驳回标记
     */
    String  BACKED_FLAG      = "backed";

    /**
     * 通过标记
     */
    String  PASSED_FLAG      = "completed";

    String  REASON_COMPLETED = "completed";

    String  REASON_DELETED   = "deleted";

    String  END_TASK_NAME    = "审批结束";
}
