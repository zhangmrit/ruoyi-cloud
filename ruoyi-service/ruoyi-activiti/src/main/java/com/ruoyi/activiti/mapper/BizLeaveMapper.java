package com.ruoyi.activiti.mapper;

import com.ruoyi.activiti.domain.BizLeave;
import java.util.List;

/**
 * 请假Mapper接口
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
public interface BizLeaveMapper
{
    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    public BizLeave selectBizLeaveById(String id);

    /**
     * 查询请假列表
     * 
     * @param actLeave 请假
     * @return 请假集合
     */
    public List<BizLeave> selectBizLeaveList(BizLeave actLeave);

    /**
     * 新增请假
     * 
     * @param actLeave 请假
     * @return 结果
     */
    public int insertBizLeave(BizLeave actLeave);

    /**
     * 修改请假
     * 
     * @param actLeave 请假
     * @return 结果
     */
    public int updateBizLeave(BizLeave actLeave);

    /**
     * 删除请假
     * 
     * @param id 请假ID
     * @return 结果
     */
    public int deleteBizLeaveById(String id);

    /**
     * 批量删除请假
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizLeaveByIds(String[] ids);
}
