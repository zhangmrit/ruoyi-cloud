package com.ruoyi.activiti.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.activiti.mapper.HistoryMapper;
import com.ruoyi.activiti.mapper.RuntimeMapper;
import com.ruoyi.activiti.service.RuntimeInfoService;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
@Slf4j
@Service
public class RuntimInfoServiceImpl implements RuntimeInfoService {
    @Autowired
    private RuntimeMapper runtimeMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    @Override
    public List<Map<String, Object>> myTasks(String userId) {
        return runtimeMapper.selectMyTasksToDo(userId);
    }

    @Override
    @Transactional
    public boolean rejected(String taskId, String rejectElemKey, String dealReason) {
        int res = 0;
        //1.历史表
        //判断是否结束
        Map<String,Object> endEvent = historyMapper.selectEndEventByTaskId(taskId);
        log.info("查询hi_taskinst结束事件的结果，{}",endEvent);
        List<Map<String,Object>> hiTask=historyMapper.selectHiTaskByTaskId(taskId);
        String ruExcutionId=(String) hiTask.get(0).get("EXECUTION_ID_");
        String _processId=(String) hiTask.get(0).get("PROC_INST_ID_");

        //2.运行表
        //判断是驳回到原点：运行表ru_task，act_ru_identitylink，ru_variable，ru_execution清除节点信息
        if ("S00000".equals(rejectElemKey)){
            if (null==endEvent || endEvent.isEmpty()){
                //删variables
                res = runtimeMapper.deleteRuVariable(taskId);
                log.info("删ru_variables结束，{}",res);

                //删除当前的任务
                //不能删除当前正在执行的任务，所以要先清除掉关联
                TaskEntity currentTaskEntity = (TaskEntity)taskService.createTaskQuery()
                        .processInstanceId(_processId).singleResult();
                currentTaskEntity.setExecutionId(null);
                taskService.saveTask(currentTaskEntity);
                taskService.deleteTask(currentTaskEntity.getId(), true);
                log.info("删ru_task结束，{}",currentTaskEntity);

                //删execution
                res = runtimeMapper.deleteRuExecution(taskId);
                log.info("删ru_execution结束，{}",res);

                //删identitylink
                res = runtimeMapper.deleteRuIdentity(taskId);
                log.info("删ru_identitylink结束，{}",res);

            }else {
                //结束了，act_hi_actinst删掉结束event
                res = historyMapper.deleteHiEndEvent(taskId);
                log.info("删掉hi_actinst中endEvent结束，{}",res);
            }
        }else {
        //判断是驳回到节点：运行表ru_task，ru_execution更改节点信息
            jumpEndActivity(ruExcutionId,rejectElemKey,dealReason);

            /*List<Map<String,Object>> hiTask=historyMapper.selectHiTaskByTaskKey(rejectElemKey);
            log.info("查询hi_taskinst历史节点（驳回节点）的结果，{}",hiTask);
            String rejectTaskId = (String) hiTask.get(0).get("ID_");
            String proInsId = (String) hiTask.get(0).get("PROC_INST_ID_");
            String proDefId = (String) hiTask.get(0).get("PROC_DEF_ID_");
            String name = (String) hiTask.get(0).get("NAME_");
            Integer priority = null==hiTask.get(0).get("PRIORITY_")?50:(Integer) hiTask.get(0).get("PRIORITY_");
            String desc = null==hiTask.get(0).get("DESCRIPTION_")?"":(String) hiTask.get(0).get("DESCRIPTION_");
            String assignee = null==hiTask.get(0).get("ASSIGNEE_")?"":(String) hiTask.get(0).get("ASSIGNEE_");
            //没结束：act_hi_actinst增加一条初始节点记录
            if (null==endEvent || endEvent.isEmpty()){
                //更改task
                res = runtimeMapper.updateRuTask(rejectElemKey);
                log.info("更改ru_task结束，{}",res);
                //更改ruExecution
                res = runtimeMapper.updateRuExecution(taskId,rejectElemKey);
                log.info("更改ruExecution结束，{}",res);
                //更改identitylink
                res = runtimeMapper.updateRuIdentitylink(rejectTaskId);
                log.info("更改ru_identitylink结束，{}",res);
                //更改variables
//                res = runtimeMapper.updateRuVariables();
            }else{
                //增加ru_execution
                MyIdGenerator myId = new MyIdGenerator();
                res = runtimeMapper.addRuExecution(myId.getNextId(),proInsId,proDefId,rejectElemKey);
                log.info("增加ru_Execution结束，{}",res);
                //增加ru_task'
                Map<String,Object> ruExecution=runtimeMapper.selectRuExecutionByTaskKey(rejectElemKey);
                log.info("查询ru_Execution结束，{}",ruExecution);
                String ruExecutionId = (String) ruExecution.get("ID_");
                myId = new MyIdGenerator();
                res = runtimeMapper.addRuTask(myId.getNextId(),ruExecutionId,proInsId,proDefId,name,desc,rejectElemKey,assignee,priority);
                log.info("增加ru_task结束，{}",res);
                Map<String,Object> ruTask=runtimeMapper.selectRuTaskByExecutionId(ruExecutionId);
                log.info("查询ru_task结束，{}",ruTask);
                String ruTaskId = (String) ruTask.get("ID_");
                //增加ru_variables
                List<Map<String,Object>> hiVariables = historyMapper.selectHiVariablesByProInsId(proInsId);
                log.info("查询hi_variables结束，{}",hiVariables);
                if (!CollectionUtils.isEmpty(hiVariables)){
                    for (Map<String,Object> hiVariable:hiVariables){
                        myId = new MyIdGenerator();
                        String id = null==hiVariable.get("ID_")?myId.getNextId():(String) hiVariable.get("ID_");
                        String type = null==hiVariable.get("VAR_TYPE_")?"":(String) hiVariable.get("VAR_TYPE_");
                        String name1 = null==hiVariable.get("NAME_")?"":(String) hiVariable.get("NAME_");
                        String text = null==hiVariable.get("TEXT_")?"":(String) hiVariable.get("TEXT_");
                        res = runtimeMapper.addRuVariables(id,type,name1,ruExecutionId,proInsId,text);
                        log.info("增加ru_variables结束，{}",res);
                    }
                }
                //增加ru_identitylink
                Map<String,Object> identitylink = historyMapper.selectIdentitylinkByTaskId(rejectTaskId);
                log.info("查询hi_identitylink结束，{}",identitylink);
                myId = new MyIdGenerator();
                String id = null==identitylink.get("ID_")?myId.getNextId():(String) identitylink.get("ID_");
                String gId = null==identitylink.get("GROUP_ID_")?"":(String) identitylink.get("GROUP_ID_");
                String uId = null==identitylink.get("USER_ID_")?"":(String) identitylink.get("USER_ID_");
                String typ = null==identitylink.get("TYPE_")?"":(String) identitylink.get("TYPE_");
                res = runtimeMapper.addRuIdentitylink(id,ruTaskId,gId,typ,uId);
                log.info("增加ru_identitylink结束，{}",res);

                //结束了，act_hi_actinst删掉结束event
                res = historyMapper.deleteHiEndEvent(taskId);
                log.info("删掉hi_actinst中endEvent结束，{}",res);
            }*/

        }
        return true;
    }


    /**
     *  根据ActivityId 查询出来想要活动Activity
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActivityImpl queryTargetActivity(String id){

        ReadOnlyProcessDefinition deployedProcessDefinition = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition("ziyouliu:1:4");
        List<ActivityImpl> activities = (List<ActivityImpl>) deployedProcessDefinition.getActivities();
        for (ActivityImpl activityImpl : activities) {
            if(activityImpl.getId().equals(id)){
                return activityImpl;
            }
        }
        return null;
    }
    /**
     * 查询当前的活动节点
     */
    public ActivityImpl qureyCurrentTask(String processDefinitionId){
//		String processDefinitionId="ziyouliu:1:4";
        Execution execution = runtimeService.createExecutionQuery().processDefinitionId(processDefinitionId).singleResult();
        String activityId = execution.getActivityId();
        ActivityImpl currentActivity = queryTargetActivity(activityId);
        log.info(currentActivity.getId()+""+currentActivity.getProperty("name"));
        return currentActivity;
    }




    /**
     * 第一种自由跳转的方式:
     *   这种方式是通过 重写命令  ，推荐这种方式进行实现(这种方式的跳转，最后可以通过taskDeleteReason 来进行查询 )
     */

    public void jumpEndActivity(String executionId,String targetActId,String reason){
/*        //当前节点
        ActivityImpl currentActivityImpl = qureyCurrentTask("ziyouliu:1:4");
        //目标节点
        ActivityImpl targetActivity = queryTargetActivity("shenchajigou");*/

        ((RuntimeServiceImpl)runtimeService).getCommandExecutor().execute(new Command<Object>() {
            public Object execute(CommandContext commandContext) {
                ExecutionEntity execution = commandContext.getExecutionEntityManager().findExecutionById(executionId);
                execution.destroyScope(reason);  //
                ProcessDefinitionImpl processDefinition = execution.getProcessDefinition();
                ActivityImpl findActivity = processDefinition.findActivity(targetActId);
                execution.executeActivity(findActivity);
                return execution;
            }

        });
        log.info("自由跳转至节点：{}-----完成",targetActId);
    }


}
