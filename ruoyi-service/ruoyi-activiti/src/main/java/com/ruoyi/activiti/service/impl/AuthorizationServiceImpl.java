package com.ruoyi.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.activiti.mapper.AuthorizationMapper;
import com.ruoyi.activiti.service.AuthorizationService;

import java.util.List;

/**
 * 
 *
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationMapper authorizationMapper;

    @Override
    public List<String> selectRoleIdsByUserId(String userId) {
        return authorizationMapper.selectRoleIdsByUserId(userId);
    }
}
