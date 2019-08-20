package com.fool.service.impl;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.mapper.FoolPermissionMapper;
import com.fool.mapper.FoolRoleMapper;
import com.fool.service.FoolPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoolPermissionServiceImpl implements FoolPermissionService {

    @Autowired
    FoolPermissionMapper permissionMapper;

    /**
     * 根据角色ID查询所有权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<FoolPermission> SelectPermissionByRoles(String roleId) {
        return null;
    }

    /**
     * 根据角色列表查询所有权限
     *
     * @param roles
     * @return
     */
    @Override
    public List<FoolPermission> SelectPermissionByRoles(List<FoolRole> roles) {
        String[] roleIds = new String[roles.size()];

        for (int i = 0; i < roles.size(); i++) {
            FoolRole role = roles.get(i);
            roleIds[i] = role.getRoleId();
        }
        return permissionMapper.SelectPermissionByRoleIds(roleIds);
    }

    /**
     * 根据用户查询该用户的所有权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<FoolPermission> SelectPermListByUserId(String userId) {
        List<FoolPermission> premList = permissionMapper.SelectPermissionByUserId(userId);
        return premList;
    }
}
