package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Users implements Serializable {
    private Integer id;
    private String name;
    private String mobile;
    private String email;
    private String wx;
    private Byte lock;
    private Byte verified;
    private String description;
    private String password;
    private String remember_token;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWx() {
        return this.wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public Byte getLock() {
        return this.lock;
    }

    public void setLock(Byte lock) {
        this.lock = lock;
    }

    public Byte getVerified() {
        return this.verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember_token() {
        return this.remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getDeleted_at() {
        return this.deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
} 

