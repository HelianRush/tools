package com.fool.service;

import com.fool.entity.FoolPermission;
import com.fool.entity.FoolRole;
import com.fool.entity.FoolUser;

import java.util.List;

public interface urpService {
    void addFoolUser(FoolUser foolUser);

    void addFoolRole(FoolRole foolRole);

    void addFoolPermission(FoolPermission foolPermission);

    List<FoolUser> queryFoolUser();

    List<FoolRole> queryFoolRole();

    List<FoolPermission> queryFoolPermission();
}
