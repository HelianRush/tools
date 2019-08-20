package com.fool.entity;

public class FoolUnRolePermission {
    private Long rpId;

    private String rpRoleid;

    private String rpPermissionid;

    private String rpDescription;

    public Long getRpId() {
        return rpId;
    }

    public void setRpId(Long rpId) {
        this.rpId = rpId;
    }

    public String getRpRoleid() {
        return rpRoleid;
    }

    public void setRpRoleid(String rpRoleid) {
        this.rpRoleid = rpRoleid == null ? null : rpRoleid.trim();
    }

    public String getRpPermissionid() {
        return rpPermissionid;
    }

    public void setRpPermissionid(String rpPermissionid) {
        this.rpPermissionid = rpPermissionid == null ? null : rpPermissionid.trim();
    }

    public String getRpDescription() {
        return rpDescription;
    }

    public void setRpDescription(String rpDescription) {
        this.rpDescription = rpDescription == null ? null : rpDescription.trim();
    }
}