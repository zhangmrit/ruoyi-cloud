package com.ruoyi.activiti.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RuntimeMapper {

    List<Map<String, Object>> selectMyTasksToDo(String userId);

    int deleteRuVariable(String taskId);

    int deleteRuExecution(String taskId);

    int deleteRuIdentity(String taskId);

    int deleteRuTask(String taskId);

    int updateRuTask(String rejectElemKey);

    int updateRuExecution(@Param("taskId") String taskId,@Param("rejectElemKey") String rejectElemKey);

    int updateRuIdentitylink(@Param("taskId") String rejectTaskId);

    int addRuExecution(@Param("id") String nextId, @Param("proInsId") String proInsId, @Param("proDefId") String proDefId,
                       @Param("rejectElemKey") String rejectElemKey);

    Map<String, Object> selectRuExecutionByTaskKey(String rejectElemKey);

    int addRuTask(@Param("id") String nextId, @Param("ruExecutionId") String ruExecutionId, @Param("proInsId") String proInsId,
                  @Param("proDefId") String proDefId,
                  @Param("name") String name, @Param("desc") String desc, @Param("rejectElemKey") String rejectElemKey,
                  @Param("assignee") String assignee, @Param("priority") Integer priority);

    int addRuVariables(@Param("id") String nextId,@Param("type") String type,@Param("name") String name,
                       @Param("ruExecutionId") String ruExecutionId,
                        @Param("proInsId")String proInsId,@Param("text") String text);

    Map<String, Object> selectRuTaskByExecutionId(String ruExecutionId);

    int addRuIdentitylink(@Param("id") String nextId,@Param("taskId") String runTaskId, @Param("gId") String gId,
                          @Param("type") String typ,@Param("uId") String uId);
}