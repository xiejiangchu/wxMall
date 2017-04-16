package com.xie.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xie.utils.DateDeserializer;
import com.xie.utils.DoubleSerializer;

import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/22 下午2:12.
 */
public class BonusType {
    private int id;
    @JsonSerialize(using = DoubleSerializer.class)
    private double money;
    private String name;
    private String description;
    private int cid1;
    private int cid2;
    private int gid;
    private int is_enable;
    @JsonSerialize(using = DoubleSerializer.class)
    private double min_amount;

    @JsonDeserialize(using = DateDeserializer.class)
    private Date start_at;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date end_at;
    private Date created_at;
    private Date updated_at;

    private String cid1_name;
    private String cid2_name;
    private String gid_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCid1() {
        return cid1;
    }

    public void setCid1(int cid1) {
        this.cid1 = cid1;
    }

    public int getCid2() {
        return cid2;
    }

    public void setCid2(int cid2) {
        this.cid2 = cid2;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(int is_enable) {
        this.is_enable = is_enable;
    }

    public double getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(double min_amount) {
        this.min_amount = min_amount;
    }

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getCid1_name() {
        return cid1_name;
    }

    public void setCid1_name(String cid1_name) {
        this.cid1_name = cid1_name;
    }

    public String getCid2_name() {
        return cid2_name;
    }

    public void setCid2_name(String cid2_name) {
        this.cid2_name = cid2_name;
    }

    public String getGid_name() {
        return gid_name;
    }

    public void setGid_name(String gid_name) {
        this.gid_name = gid_name;
    }
}
