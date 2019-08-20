package com.fool.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FoolRole implements Serializable {
    private String roleId;

    private String roleName;

    private String roleDescription;

    private boolean configFlag;

    private Set<FoolUser> foolUser = new HashSet<>();

    private Set<FoolPermission> foolPermission = new HashSet<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    public Set<FoolUser> getFoolUser() {
        return foolUser;
    }

    public void setFoolUser(Set<FoolUser> foolUser) {
        this.foolUser = foolUser;
    }

    public Set<FoolPermission> getFoolPermission() {
        return foolPermission;
    }

    public void setFoolPermission(Set<FoolPermission> foolPermission) {
        this.foolPermission = foolPermission;
    }

    public boolean isConfigFlag() {
        return configFlag;
    }

    public void setConfigFlag(boolean configFlag) {
        this.configFlag = configFlag;
    }

    @Override
    public String toString() {
        return "FoolRole{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", configFlag=" + configFlag +
                ", foolUser=" + foolUser +
                ", foolPermission=" + foolPermission +
                '}';
    }
}