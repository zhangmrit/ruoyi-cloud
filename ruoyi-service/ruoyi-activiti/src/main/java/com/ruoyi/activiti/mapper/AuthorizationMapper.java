package com.ruoyi.activiti.mapper;

import java.util.List;

public interface AuthorizationMapper {

    List<String> selectRoleIdsByUserId(String userId);
}