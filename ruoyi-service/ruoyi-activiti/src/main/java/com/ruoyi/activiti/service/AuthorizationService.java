package com.ruoyi.activiti.service;

import java.util.List;

/**
 * @Auther: Ace Lee
 * @Date: 2019/3/7 16:55
 */
public interface AuthorizationService {

    List<String> selectRoleIdsByUserId(String userId);
}
