//package com.ruoyi.activiti.cover;
//
//import java.awt.Color;
//
///**
// * <p>File：WorkflowConstants.java</p>
// * <p>Title: 自定义常量</p>
// * <p>Description:</p>
// * <p>Copyright: Copyright (c) 2020 2020年1月15日 下午6:37:26</p>
// * <p>Company: zmrit.com </p>
// * @author zmr
// * @version 1.0
// */
//public final class WorkflowConstants
//{
//    /**businessKey**/
//    public static final String  WORKLOW_BUSINESS_KEY            = "businessKey";
//
//    /**按钮网关**/
//    public static final String  WAY_TYPE                        = "wayType";
//
//    /**按钮网关**/
//    public static final String  WAY_TYPE_PREFIX                 = "way_type_";
//
//    /**项目id**/
//    public static final String  PROJECT_ID                      = "projectId";
//
//    /**核心企业Id变量**/
//    public static final String  CORE_ENTERPRISE_ID              = "coreEnterpriseId";
//
//    /**链属企业Id变量**/
//    public static final String  CHAIN_ENTERPRISE_ID             = "chainEnterpriseId";
//
//    /**银行企业Id变量**/
//    public static final String  BANK_ENTERPRISE_ID              = "bankEnterpriseId";
//
//    /**保理公司Id变量**/
//    public static final String  BAOLI_ENTERPRISE_ID             = "baoliEnterpriseId";
//
//    /**立账开立企业Id变量**/
//    public static final String  START_ENTERPRISE_ID             = "startEnterpriseId";
//
//    /**立账合作企业Id变量**/
//    public static final String  PARTNER_ENTERPRISE_ID           = "partnerEnterpriseId";
//
//    /**母公司企业id**/
//    public static final String  PARENT_ENTERPRISE_ID            = "parentEnterpriseId";
//
//    /**指定签收企业id**/
//    public static final String  RECEIVE_ENTERPRISE_ID           = "receiveEnterpriseId";
//
//    /**转出方企业Id变量**/
//    public static final String  TRANSFER_ENTERPRISE_ID          = "transEnterpriseId";
//
//    /**指定签收企业id**/
//    public static final String  AC_TASK_ID                      = "acTaskId";
//
//    /**企业所有角色**/
//    public static final String  ENT_ALL_ROLE                    = "all";
//
//    /**运营所有角色**/
//    public static final String  OPER_ALL_ROLE                   = "oper";
//
//    /**流程定义缓存时间**/
//    public static final int     PROCESS_DEFINITION_CACHE        = 60;
//
//    /**流程实例激活**/
//    public static final int     PROCESS_INSTANCE_ACTIVE         = 1;
//
//    /**流程实例挂起**/
//    public static final int     PROCESS_INSTANCE_SUSPEND        = 0;
//
//    /**读取图片**/
//    public static final String  READ_IMAGE                      = "image";
//
//    /**读取xml**/
//    public static final String  READ_XML                        = "xml";
//
//    /**流程激活**/
//    public static final Integer ACTIVE_PROCESSDEFINITION        = 1;
//
//    /**流程挂起**/
//    public static final Integer SUSPEND_PROCESSDEFINITION       = 2;
//
//    /**流程状态：0-全部，1-正常，2-已挂起**/
//    public static final int     QUERY_ALL                       = 0;
//
//    public static final int     QUERY_NORMAL                    = 1;
//
//    public static final int     QUERY_SUSPENDED                 = 2;
//
//    /**流程实例状态：0-全部，1-正常，2-已删除**/
//    public static final int     INSTANCE_ALL                    = 0;
//
//    public static final int     INSTANCE_NOT_DELETED            = 1;
//
//    public static final int     INSTANCE_DELETED                = 2;
//
//    /** 系统管理员ID **/
//    public static final String  INTERFACE_SYSTEM_ID             = "-1";
//
//    /** 系统管理员名称 **/
//    public static final String  INTERFACE_SYSTEM_NAME           = "系统操作";
//
//    /** 流程部署类型:1-启动并激活，2-启动即挂起 **/
//    public static final int     PROCESS_START_ACTIVE            = 1;
//
//    public static final int     PROCESS_START_SUSPEND           = 2;
//
//    /** 用于标识流程项目配置信息校验结果：1：新流程，2：新版本， 3：流程类别有误  **/
//    public static final int     CHECK_NEW_PROCESS               = 1;
//
//    public static final int     CHECK_NEW_VERSION               = 2;
//
//    public static final int     CHECK_ERROR_PROCESS_TYPE        = 3;
//
//    /** 默认网关条件值 **/
//    public static final Integer default_GATEWAY_CONDITION_VALUE = 1;
//
//    /** 工作流-业务状态表数据类型：1-工作流状态，2-业务状态 **/
//    public static final Integer PROCESS_STATUS                  = 1;
//
//    public static final Integer BIZNESS_STATUS                  = 2;
//
//    /** 新增流程时标识：1-直接保存，2-提示覆盖 **/
//    public static final Integer PROCESS_STATUS_SAVE             = 1;
//
//    public static final Integer BIZNESS_STATUS_WARN             = 2;
//
//    /** 模板类型标识：1-新创建或直接导入的模板，2-默认模板生成 **/
//    public static final Integer MODEL_TYPE_1                    = 1;
//
//    public static final Integer MODEL_TYPE_2                    = 2;
//
//    /** 查询流程定义标识：1-查询最新版本流程定义，2-查询所有版本 **/
//    public static final Integer QUERY_PROCESS_LATEST_VERSION    = 1;
//
//    public static final Integer QUERY_PROCESS_ALL_VERSION       = 2;
//
//    /** 按钮网关 通过1 */
//    public static final String  WAY_TYPE_PASS                   = "1";
//
//    /** 按钮网关 驳回或结束0 */
//    public static final String  WAY_TYPE_REJECT                 = "0";
//
//    /** 按钮网关 退回2 */
//    public static final String  WAY_TYPE_BACK                   = "2";
//
//    /**任务参数为空**/
//    public static final int     TASK_CHECK_PARAM_NULL           = -1;
//
//    /**任务已办理**/
//    public static final int     TASK_CHECK_COMPLETED            = 1;
//
//    /**无权限办理**/
//    public static final int     TASK_CHECK_NO_PERMISSIONS       = 2;
//
//    /**任务校验通过**/
//    public static final int     TASK_CHECK_PASS                 = 0;
//
//    /** 动态流程图颜色定义 **/
//    public static final Color   COLOR_NORMAL                    = new Color(0, 205, 0);
//
//    public static final Color   COLOR_CURRENT                   = new Color(255, 0, 0);
//
//    /** 定义生成流程图时的边距(像素) **/
//    public static final int     PROCESS_PADDING                 = 5;
//}