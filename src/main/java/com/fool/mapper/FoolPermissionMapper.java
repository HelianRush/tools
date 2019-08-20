package com.fool.mapper;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
@Repository
public interface FoolPermissionMapper {
    int deleteByPrimaryKey(String perId);

    int insert(FoolPermission record);

    int insertSelective(FoolPermission record);

    FoolPermission selectByPrimaryKey(String perId);

    int updateByPrimaryKeySelective(FoolPermission record);

    int updateByPrimaryKey(FoolPermission record);

    List<FoolPermission> queryFoolPermission();

    /**
     * 根据角色ID查询所有权限
     *
     * @param roleIds
     * @return
     */
    List<FoolPermission> SelectPermissionByRoleIds(String[] roleIds);

    /**
     * 根据用户查询该用户的所有权限
     *
     * @param userId
     * @return
     */
    List<FoolPermission> SelectPermissionByUserId(String userId);

}