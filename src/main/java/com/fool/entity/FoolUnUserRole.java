package com.fool.entity;

public class FoolUnUserRole {
    private Long urId;

    private String urUserid;

    private String urRoleid;

    private String urDescription;

    public Long getUrId() {
        return urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public String getUrUserid() {
        return urUserid;
    }

    public void setUrUserid(String urUserid) {
        this.urUserid = urUserid == null ? null : urUserid.trim();
    }

    public String getUrRoleid() {
        return urRoleid;
    }

    public void setUrRoleid(String urRoleid) {
        this.urRoleid = urRoleid == null ? null : urRoleid.trim();
    }

    public String getUrDescription() {
        return urDescription;
    }

    public void setUrDescription(String urDescription) {
        this.urDescription = urDescription == null ? null : urDescription.trim();
    }
}