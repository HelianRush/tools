package com.fool.service;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;

import java.util.List;

public interface FoolPermissionService {

    // 根据角色ID查询所有权限
    List<FoolPermission> SelectPermissionByRoles(String roleId);

    //  根据角色列表查询所有权限
    List<FoolPermission> SelectPermissionByRoles(List<FoolRole> roles);

    // 根据用户查询所有全权限
    List<FoolPermission> SelectPermListByUserId(String userId);
}
