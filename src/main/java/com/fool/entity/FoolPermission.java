package com.fool.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FoolPermission implements Serializable {
    private String perId;

    private String perName;

    private Long perLevel;

    private String perDescription;

    private Set<FoolRole> foolRole = new HashSet<>();

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId == null ? null : perId.trim();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public Long getPerLevel() {
        return perLevel;
    }

    public void setPerLevel(Long perLevel) {
        this.perLevel = perLevel;
    }

    public String getPerDescription() {
        return perDescription;
    }

    public void setPerDescription(String perDescription) {
        this.perDescription = perDescription == null ? null : perDescription.trim();
    }

    public Set<FoolRole> getFoolRole() {
        return foolRole;
    }

    public void setFoolRole(Set<FoolRole> foolRole) {
        this.foolRole = foolRole;
    }

    @Override
    public String toString() {
        return "FoolPermission{" +
                "perId='" + perId + '\'' +
                ", perName='" + perName + '\'' +
                ", perLevel=" + perLevel +
                ", perDescription='" + perDescription + '\'' +
                ", foolRole=" + foolRole +
                '}';
    }
}