package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Bonus implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer type_id;
    private Double money;
    private String name;
    private String desc;
    private Integer category_id1;
    private Integer category_id2;
    private Integer goods_id;
    private Double min_amount;
    private Integer is_enable;
    private Timestamp begin;
    private Timestamp end;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType_id() {
        return this.type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCategory_id1() {
        return this.category_id1;
    }

    public void setCategory_id1(Integer category_id1) {
        this.category_id1 = category_id1;
    }

    public Integer getCategory_id2() {
        return this.category_id2;
    }

    public void setCategory_id2(Integer category_id2) {
        this.category_id2 = category_id2;
    }

    public Integer getGoods_id() {
        return this.goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Double getMin_amount() {
        return this.min_amount;
    }

    public void setMin_amount(Double min_amount) {
        this.min_amount = min_amount;
    }

    public Integer getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(Integer is_enable) {
        this.is_enable = is_enable;
    }

    public Timestamp getBegin() {
        return this.begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return this.end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
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
} 

