package com.ruoyi.activiti.controller;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.activiti.service.HistoryInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 历史管理
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 11:43
 */
@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryInfoService historyInfoService;
    @Autowired
    private HistoryService historyService;

    /**
     * 我的审批记录
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/tasks/myc")
    public Object myTasksCompleted(@RequestParam("userId") String userId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> hiTasks = historyInfoService.myTasksCompleted(userId);
        setVariables(list, hiTasks);
        return list;
    }

    /**
     * 我发起的记录
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/process/mys")
    public Object myProcessStarted(@RequestParam("userId") String userId) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Map<String, Object>> hiPros = historyInfoService.myProcessStarted(userId);
        setVariables(list, hiPros);
        return list;
    }

    private void setVariables(List<Map<String, Object>> listNew, List<Map<String, Object>> listOld) {
        if (!CollectionUtils.isEmpty(listOld)) {
            for (Map<String, Object> hipro : listOld) {
                List<HistoricVariableInstance> variables = historyService.createHistoricVariableInstanceQuery().
                        processInstanceId((String) hipro.get("PROC_INST_ID_")).list();
                if (!CollectionUtils.isEmpty(variables)) {
                    for (HistoricVariableInstance variable : variables) {
                        hipro.put(variable.getVariableName(), variable.getValue());
                    }
                }
                listNew.add(hipro);
            }
        }
    }
}
