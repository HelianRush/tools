package com.fool.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoolUser implements Serializable {
    private String userId;

    private String account;

    private String username;

    private String password;

    private String salt;

    private String plaintext;

    private Boolean lockStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userLastLoginTime;

    public String getUserId() {
        return userId;
    }

    private List<FoolRole> foolRole = new ArrayList<>();

    /* Getter  Setter*/

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext == null ? null : plaintext.trim();
    }

    public Boolean getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public List<FoolRole> getFoolRole() {
        return foolRole;
    }

    public void setFoolRole(List<FoolRole> foolRole) {
        this.foolRole = foolRole;
    }

    @Override
    public String toString() {
        return "FoolUser{" +
                "userId='" + userId + '\'' +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", plaintext='" + plaintext + '\'' +
                ", lockStatus=" + lockStatus +
                ", userCreateTime=" + userCreateTime +
                ", userLastLoginTime=" + userLastLoginTime +
                ", foolRole=" + foolRole +
                '}';
    }
}