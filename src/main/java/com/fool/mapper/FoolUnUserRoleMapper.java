package com.fool.mapper;

import com.fool.entity.FoolUnUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoolUnUserRoleMapper {

    // 根据用户，查询用户下的用户角色
    List<FoolUnUserRole> SelectUnUserRoleByUserAll(String userId);

    // 根据用户ID，删除所有角色配置
    int DeleteRolesByUser(String userId);

    // 根据RoleId数组，批量删除参数之外的数据
    int DeleteOldRoleConfig(List<Long> delUnIds);
    // int DeleteOldRoleConfig(String userId, String[] arrayRoles);

    // 新增角色配置
    int InsertFoolUnUserRole(FoolUnUserRole foolUnUserRole);
}