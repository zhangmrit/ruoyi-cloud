package com.ruoyi.system.feign;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruoyi.common.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.factory.RemoteUserFallbackFactory;

/**
 * 用户 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    @GetMapping("user/get/{userId}")
    public SysUser selectSysUserByUserId(@PathVariable("userId") long userId);

    @GetMapping("user/find/{username}")
    public SysUser selectSysUserByUsername(@PathVariable("username") String username);

    @PostMapping("user/update/login")
    public R updateUserLoginRecord(@RequestBody SysUser user);

    /**
     * 查询拥有当前角色的所有用户
     * @param auditor
     * @return
     * @author zmr
     */
    @GetMapping("user/hasRoles")
    public Set<Long> selectUserIdsHasRoles(@RequestParam("roleIds") String roleIds);

    /**
     * 查询所有当前部门中的用户
     * 
     * @param deptId
     * @return
     * @author zmr
     */
    @GetMapping("user/inDepts")
    public Set<Long> selectUserIdsInDepts(@RequestParam("deptIds") String deptIds);
}
