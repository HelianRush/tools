package com.fool.mapper;

import com.fool.entity.FoolUser;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface FoolUserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(FoolUser record);

    int insertSelective(FoolUser record);

    FoolUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(FoolUser record);

    int updateByPrimaryKey(FoolUser record);

    // 根据用户名查询
    FoolUser QueryByUsername(@Param("username") String username);

    // 根据账号查询用户 唯一
    FoolUser FindByUsername(FoolUser foolUser);

    // 根据账号查询用户 - 依赖查询
    List<Map<String, Object>> QueryByUsernameToMany(FoolUser foolUser);

    // 查询用户列表，用于用户、角色、权限配置时展示  获取：user_id、username
    List<FoolUser> queryUserListForConfig();
}