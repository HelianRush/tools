package com.fool.service.impl;

import com.fool.entity.FoolRole;
import com.fool.mapper.FoolRoleMapper;
import com.fool.service.FoolRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoolRoleServiceImpl implements FoolRoleService {

    @Autowired
    FoolRoleMapper roleMapper;

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<FoolRole> queryAllRoles() {
        return null;
    }

    /***
     * 根据用户ID查询角色
     *
     * @return
     */
    @Override
    public List<FoolRole> SelectRoleByUserId(String userId) {
        return roleMapper.SelectRoleByUserId(userId);
    }

    /**
     * 根据用户匹配角色
     * 页面需要展示全部角色
     * FoolRole.configFlag字段匹配页面<input type="checkbox">
     */
    @Override
    public List<FoolRole> SelectRolesByUser(String userId) {

        // 说明：FoolRole.ConfigFlag 匹配 checkbox

        String[] rolesId = null; // 用户所匹配的角色ID数组

        // 用户的角色
        List<FoolRole> userRoleList = roleMapper.SelectRoleByUserId(userId);
        if (0 < userRoleList.size()) {
            rolesId = new String[userRoleList.size()];
            for (int i = 0; i < userRoleList.size(); i++) {
                userRoleList.get(i).setConfigFlag(true);
                rolesId[i] = userRoleList.get(i).getRoleId();
            }
        }

        /*
         * 如果 rolesId 或 userRoleList 等于 0、null
         * ----- 返回全部RoleList
         * 如果 rolesId 不等 0、null
         * ----- 查询用户不拥有的RoleList
         * */
        if (null == rolesId || 0 >= userRoleList.size()) {
            List<FoolRole> roleList = roleMapper.queryAllRoles();
            for (int i = 0; i < roleList.size(); i++) {
                roleList.get(i).setConfigFlag(false);
            }
            return roleList;
        } else {
            // 用户没有的角色
            List<FoolRole> userNoneRoleList = roleMapper.SelectUserNoneRole(rolesId);
            if (0 < userNoneRoleList.size()) {
                for (int i = 0; i < userNoneRoleList.size(); i++) {
                    userNoneRoleList.get(i).setConfigFlag(false);
                }
                userRoleList.addAll(userNoneRoleList);
            }
            return userRoleList;
        }
    }
}
