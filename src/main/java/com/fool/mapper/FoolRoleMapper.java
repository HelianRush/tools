package com.fool.mapper;

import com.fool.entity.FoolRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
@Repository
public interface FoolRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(FoolRole record);

    int insertSelective(FoolRole record);

    FoolRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(FoolRole record);

    int updateByPrimaryKey(FoolRole record);

    List<FoolRole> queryFoolRole();

    // 查询全部
    List<FoolRole> queryAllRoles();

    // 根据用户ID查询角色
    List<FoolRole> SelectRoleByUserId(String userId);

    // 根据用户ID查询用户没匹配的角色
    List<FoolRole> SelectUserNoneRole(String[] rolesId);
}