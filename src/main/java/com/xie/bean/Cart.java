package com.xie.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xie.utils.DoubleSerializer;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = { "handler" })
public class Cart implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer gid;
    private Integer spec;
    private Integer amount;
    private Date created_at;
    private Date updated_at;

    private User user;
    private Item item;
    private ItemSpec itemSpec;

    @JsonSerialize(using = DoubleSerializer.class)
    private double subTotal;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getSpec() {
        return spec;
    }

    public void setSpec(Integer spec) {
        this.spec = spec;
    }

    public ItemSpec getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(ItemSpec itemSpec) {
        this.itemSpec = itemSpec;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}

