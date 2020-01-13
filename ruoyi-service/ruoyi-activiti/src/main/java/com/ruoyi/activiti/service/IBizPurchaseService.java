package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.BizPurchase;
import java.util.List;

/**
 * 报销Service接口
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
public interface IBizPurchaseService
{
    /**
     * 查询报销
     * 
     * @param id 报销ID
     * @return 报销
     */
    public BizPurchase selectBizPurchaseById(String id);

    /**
     * 查询报销列表
     * 
     * @param bizPurchase 报销
     * @return 报销集合
     */
    public List<BizPurchase> selectBizPurchaseList(BizPurchase bizPurchase);

    /**
     * 新增报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
    public int insertBizPurchase(BizPurchase bizPurchase);

    /**
     * 修改报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
    public int updateBizPurchase(BizPurchase bizPurchase);

    /**
     * 批量删除报销
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizPurchaseByIds(String ids);

    /**
     * 删除报销信息
     * 
     * @param id 报销ID
     * @return 结果
     */
    public int deleteBizPurchaseById(String id);
}
