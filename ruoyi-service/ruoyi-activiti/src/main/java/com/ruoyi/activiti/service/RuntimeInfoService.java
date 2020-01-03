package com.ruoyi.activiti.service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
public interface RuntimeInfoService {

    List<Map<String, Object>> myTasks(String userId);

    boolean rejected(String taskId, String rejectElemKey, String dealReason);
}
