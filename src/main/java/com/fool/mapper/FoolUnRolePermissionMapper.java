package com.fool.mapper;

import com.fool.entity.FoolUnRolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoolUnRolePermissionMapper {
    int deleteByPrimaryKey(Long rpId);

    int insert(FoolUnRolePermission record);

    int insertSelective(FoolUnRolePermission record);

    FoolUnRolePermission selectByPrimaryKey(Long rpId);

    int updateByPrimaryKeySelective(FoolUnRolePermission record);

    int updateByPrimaryKey(FoolUnRolePermission record);
}