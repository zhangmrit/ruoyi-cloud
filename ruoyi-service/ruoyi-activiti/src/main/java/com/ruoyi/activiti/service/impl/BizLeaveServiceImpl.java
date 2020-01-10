package com.ruoyi.activiti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.domain.BizLeave;
import com.ruoyi.activiti.mapper.BizLeaveMapper;
import com.ruoyi.activiti.service.IBizLeaveService;
import com.ruoyi.common.core.text.Convert;

/**
 * 请假Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
@Service
public class BizLeaveServiceImpl implements IBizLeaveService
{
    @Autowired
    private BizLeaveMapper leaveMapper;

    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    @Override
    public BizLeave selectBizLeaveById(String id)
    {
        return leaveMapper.selectBizLeaveById(id);
    }

    /**
     * 查询请假列表
     * 
     * @param bizLeave 请假
     * @return 请假
     */
    @Override
    public List<BizLeave> selectBizLeaveList(BizLeave bizLeave)
    {
        return leaveMapper.selectBizLeaveList(bizLeave);
    }

    /**
     * 新增请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int insertBizLeave(BizLeave bizLeave)
    {
        return leaveMapper.insertBizLeave(bizLeave);
    }

    /**
     * 修改请假
     * 
     * @param bizLeave 请假
     * @return 结果
     */
    @Override
    public int updateBizLeave(BizLeave bizLeave)
    {
        return leaveMapper.updateBizLeave(bizLeave);
    }

    /**
     * 删除请假对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveByIds(String ids)
    {
        return leaveMapper.deleteBizLeaveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请假信息
     * 
     * @param id 请假ID
     * @return 结果
     */
    public int deleteBizLeaveById(String id)
    {
        return leaveMapper.deleteBizLeaveById(id);
    }
}
