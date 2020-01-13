package com.ruoyi.activiti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.domain.BizPurchase;
import com.ruoyi.activiti.mapper.BizPurchaseMapper;
import com.ruoyi.activiti.service.IBizPurchaseService;

/**
 * 报销Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
@Service
public class BizPurchaseServiceImpl implements IBizPurchaseService
{
    @Autowired
    private BizPurchaseMapper purchaseMapper;

    /**
     * 查询报销
     * 
     * @param id 报销ID
     * @return 报销
     */
    @Override
    public BizPurchase selectBizPurchaseById(String id)
    {
        return purchaseMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询报销列表
     * 
     * @param bizPurchase 报销
     * @return 报销
     */
    @Override
    public List<BizPurchase> selectBizPurchaseList(BizPurchase bizPurchase)
    {
        return purchaseMapper.select(bizPurchase);
    }

    /**
     * 新增报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
    @Override
    public int insertBizPurchase(BizPurchase bizPurchase)
    {
        return purchaseMapper.insertSelective(bizPurchase);
    }

    /**
     * 修改报销
     * 
     * @param bizPurchase 报销
     * @return 结果
     */
    @Override
    public int updateBizPurchase(BizPurchase bizPurchase)
    {
        return purchaseMapper.updateByPrimaryKeySelective(bizPurchase);
    }

    /**
     * 删除报销对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizPurchaseByIds(String ids)
    {
        return purchaseMapper.deleteByIds(ids);
    }

    /**
     * 删除报销信息
     * 
     * @param id 报销ID
     * @return 结果
     */
    public int deleteBizPurchaseById(String id)
    {
        return purchaseMapper.deleteByPrimaryKey(id);
    }
}
