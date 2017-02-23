package com.xie.bean;

import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/22 下午2:12.
 */
public class BonusType {
    private int id;
    private String name;
    private String desc;
    private int is_enable;
    private double min_amount;
    private Date available_start;
    private Date available_end;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Date getAvailable_start() {
        return available_start;
    }

    public void setAvailable_start(Date available_start) {
        this.available_start = available_start;
    }

    public Date getAvailable_end() {
        return available_end;
    }

    public void setAvailable_end(Date available_end) {
        this.available_end = available_end;
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
}
