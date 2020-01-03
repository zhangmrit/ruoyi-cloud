package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.ActRuTask;

public interface ActRuTaskMapper
{
    int deleteByPrimaryKey(String id);

    int insert(ActRuTask record);

    int insertSelective(ActRuTask record);

    ActRuTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActRuTask record);

    int updateByPrimaryKey(ActRuTask record);

    List<ActRuTask> selectAll();
}