package com.ruoyi.activiti.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.mapper.HistoryMapper;
import com.ruoyi.activiti.service.IHistoryInfoService;
import com.ruoyi.activiti.vo.HiProcInsVo;

/**
 * 
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
@Service
public class HistoryInfoServiceImpl implements IHistoryInfoService
{
    @Autowired
    private HistoryMapper historyMapper;

    /* (non-Javadoc)
     * @see com.ruoyi.activiti.service.IHistoryInfoService#getHiProcInsListDone(com.ruoyi.activiti.vo.HiProcInsVo)
     */
    @Override
    public List<HiProcInsVo> getHiProcInsListDone(HiProcInsVo hiProcInsVo)
    {
        return historyMapper.getHiProcInsListDone(hiProcInsVo);
    }
}
