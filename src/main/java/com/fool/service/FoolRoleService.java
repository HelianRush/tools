package com.fool.service;

import com.fool.entity.FoolRole;

import java.util.List;

public interface FoolRoleService {

    List<FoolRole> queryAllRoles();

    List<FoolRole> SelectRoleByUserId(String userId);

    List<FoolRole> SelectRolesByUser(String userId);


}
