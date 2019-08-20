package com.fool.service.impl;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.entity.FoolUser;
import com.fool.mapper.FoolPermissionMapper;
import com.fool.mapper.FoolRoleMapper;
import com.fool.mapper.FoolUserMapper;
import com.fool.service.urpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class urpServiceImpl implements urpService {

    @Autowired
    private FoolUserMapper foolUserMapper;
    @Autowired
    private FoolRoleMapper foolRoleMapper;
    @Autowired
    private FoolPermissionMapper foolPermissionMapper;

    public void addFoolUser(FoolUser foolUser) {
        Date date = new Date();
        foolUser.setUserId(String.valueOf(date.getTime()));
        foolUser.setUserCreateTime(date);
        foolUser.setUserLastLoginTime(date);
        foolUserMapper.insert(foolUser);
    }

    public void addFoolRole(FoolRole foolRole) {
        Date date = new Date();
        foolRole.setRoleId(String.valueOf(date.getTime()));
        foolRoleMapper.insert(foolRole);
    }

    public void addFoolPermission(FoolPermission foolPermission) {
        Date date = new Date();
        foolPermission.setPerId(String.valueOf(date.getTime()));
        foolPermissionMapper.insert(foolPermission);
    }

    public List<FoolUser> queryFoolUser() {
        foolUserMapper.QueryByUsername(null);
        return null;
    }

    public List<FoolRole> queryFoolRole() {
        return foolRoleMapper.queryFoolRole();
    }

    public List<FoolPermission> queryFoolPermission() {
        return foolPermissionMapper.queryFoolPermission();
    }

}
