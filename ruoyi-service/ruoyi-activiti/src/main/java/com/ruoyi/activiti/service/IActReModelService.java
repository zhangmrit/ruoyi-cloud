package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.ActReModel;
import java.util.List;

/**
 * 流程设计模型部署Service接口
 * 
 * @author ruoyi
 * @date 2019-11-26
 */
public interface IActReModelService 
{
    /**
     * 查询流程设计模型部署
     * 
     * @param id 流程设计模型部署ID
     * @return 流程设计模型部署
     */
    public ActReModel selectActReModelById(String id);

    /**
     * 查询流程设计模型部署列表
     * 
     * @param actReModel 流程设计模型部署
     * @return 流程设计模型部署集合
     */
    public List<ActReModel> selectActReModelList(ActReModel actReModel);

    /**
     * 新增流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    public int insertActReModel(ActReModel actReModel);

    /**
     * 修改流程设计模型部署
     * 
     * @param actReModel 流程设计模型部署
     * @return 结果
     */
    public int updateActReModel(ActReModel actReModel);

    /**
     * 批量删除流程设计模型部署
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActReModelByIds(String ids);

    /**
     * 删除流程设计模型部署信息
     * 
     * @param id 流程设计模型部署ID
     * @return 结果
     */
    public int deleteActReModelById(String id);
}
