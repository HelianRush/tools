package com.fool.service;

import com.fool.entity.FoolUnUserRole;

import java.util.List;

public interface FoolUnUserRoleService {
    List<FoolUnUserRole> SelectUnUserRoleByUserAll(String userId);

    boolean EditRoleByUser(String userId, String[] arrayRoles);

    int DeleteRolesByUser(String userId);
}
