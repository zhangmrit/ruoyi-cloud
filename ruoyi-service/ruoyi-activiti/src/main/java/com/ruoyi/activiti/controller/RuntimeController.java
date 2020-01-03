package com.ruoyi.activiti.controller;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.ruoyi.activiti.service.RuntimeInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运行接口
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 11:43
 */
@Slf4j
@RestController
@RequestMapping("runtime")
public class RuntimeController {

    @Autowired
    private RuntimeInfoService runtimeInfoService;

    @Autowired
    private TaskService taskService;


    /**
     * 完成任务
     *
     * 这两个是本节点的业务数据---增加到所有节点
     *      dealReason: 处理原因
     *      dealType: 处理类型
     *
     * 这两个是本节点的业务数据---增加到本节点
     *      dealUserId: 操作人id
     *      dealUnitId: 操作人单位id
     *
     * 注意顺序，先给值，后完成。
     *
     * @param taskId
     * @return
     */
    @PostMapping(value = "tasks/do/{taskId}")
    public Object tasks(@PathVariable String taskId, @RequestBody Map<String, Object> params ) {
        boolean taskDo = true;
        if (null==params || params.isEmpty()){
            taskService.complete(taskId);
            return taskDo;
        }
        //驳回
        String dealType = (String) params.get("dealType");
        String dealReason = (String) params.get("dealReason");
        if ("1".equals(dealType)){
            //获取驳回节点定义key
            try {
                String rejectElemKey = (String) params.get("rejectElemKey");
                if ("S00000".equals(rejectElemKey)){
                    completeTasks(taskId, params);
                }else {
                    taskService.setVariableLocal(taskId,"dealUserId",params.get("dealUserId"));
                    taskService.setVariableLocal(taskId,"dealUnitId",params.get("dealUnitId"));
                    taskService.setVariable(taskId,"dealType",dealType);
                    taskService.setVariable(taskId,"dealReason",dealReason);
                }
                taskDo = runtimeInfoService.rejected(taskId,rejectElemKey,dealReason);
            } catch (Exception e) {
                taskDo = false;
                log.error("驳回处理异常：{}",e);
            }
        //通过
        }else if ("0".equals(dealType)){
            completeTasks(taskId, params);
        }
        return taskDo;
    }

    private void completeTasks(@PathVariable String taskId, @RequestBody Map<String, Object> params) {
        log.info("完成任务参数：taskId={} ,params={}",taskId,params);
        Map<String, Object> variables = new HashMap<>();
        variables.put("dealUserId",params.get("dealUserId"));
        variables.put("dealUnitId",params.get("dealUnitId"));
        taskService.setVariablesLocal(taskId,variables);
        variables = new HashMap<>();
        variables.put("dealType",params.get("dealType"));
        variables.put("dealReason",params.get("dealReason"));
        taskService.complete(taskId,variables);
        log.info("完成任务：任务ID："+taskId);
    }

    /**
     * 根据任务ID查询当前业务数据
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/tasks/buss")
    public Object bussNow(@RequestParam("taskId") String taskId) {
        Map<String, Object> variables = taskService.getVariables(taskId);
        return variables;
    }


    /**
     * 我的待办任务
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/tasks/ing")
    public Object myTasks(@RequestParam("userId") String userId) {
        List<Map<String,Object>> list = new ArrayList<>();
        List<Map<String,Object>> tasks = runtimeInfoService.myTasks(userId);
        if (!CollectionUtils.isEmpty(tasks)){
            for (Map<String,Object> task:tasks){
                Map<String, Object> variables = taskService.getVariables((String) task.get("ID_"));
                task.putAll(variables);
                list.add(task);
            }
        }
        return list;
        /*//节点指定的人
        List<RuTask> list = new ArrayList<>();
        List<Task> listTask = taskService.createTaskQuery()
                .taskAssignee(userId)
                .orderByTaskCreateTime().asc()
                .active()
                .list();
        if (listTask != null && listTask.size() > 0) {
            for (Task task : listTask) {
                list.add(new RuTask(task));
                log.info("流程定义的ID："+task.getProcessDefinitionId());
                log.info("流程实例的ID："+task.getProcessInstanceId());
                log.info("执行对象ID："+task.getExecutionId());
                log.info("任务ID："+task.getId());
                log.info("任务名称："+task.getName());
                log.info("任务创建的时间："+task.getCreateTime());
                log.info("================================");
            }
        }

        //节点指定的组
        //先查出人的角色
        List<String> groupIds = authorizationService.selectRoleIdsByUserId(userId);
        if (!CollectionUtils.isEmpty(groupIds)){
            List<Task> lists = taskService.createTaskQuery()
                    .taskCandidateGroupIn(groupIds)
                    .orderByTaskCreateTime().asc()
                    .active()
                    .list();
            if (lists != null && lists.size() > 0) {
                for (Task task : lists) {
                    list.add(new RuTask(task));
                    log.info("G流程定义的ID："+task.getProcessDefinitionId());
                    log.info("G流程实例的ID："+task.getProcessInstanceId());
                    log.info("G执行对象ID："+task.getExecutionId());
                    log.info("G任务ID："+task.getId());
                    log.info("G任务名称："+task.getName());
                    log.info("G任务创建的时间："+task.getCreateTime());
                    log.info("G================================");
                }
            }
        }
        return list;*/
    }

}
