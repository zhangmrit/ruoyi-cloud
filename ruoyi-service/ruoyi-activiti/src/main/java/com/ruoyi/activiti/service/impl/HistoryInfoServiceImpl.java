package com.ruoyi.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.mapper.HistoryMapper;
import com.ruoyi.activiti.service.HistoryInfoService;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
@Service
public class HistoryInfoServiceImpl implements HistoryInfoService {
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<Map<String, Object>> myTasksCompleted(String userId) {
        return historyMapper.selectMyTasksCompleted(userId);
    }

    @Override
    public List<Map<String, Object>> myProcessStarted(String userId) {
        return historyMapper.selectMyProcessStarted(userId);
    }
}
