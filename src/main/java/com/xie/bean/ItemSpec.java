package com.xie.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xie.utils.DoubleSerializer;

import java.util.Date;

/**
 * @Author xie
 * @Date 17/2/22 下午1:53.
 */
public class ItemSpec {
    private int id;
    private int gid;
    private int is_remain;
    private int is_online;
    private int is_active;
    private int is_rough;
    private int is_promote;
    private int is_delete;
    private int status;
    private int sort;

    @JsonSerialize(using = DoubleSerializer.class)
    private double weight;

    private int min;
    private int max;
    @JsonSerialize(using = DoubleSerializer.class)
    private double market_price;
    @JsonSerialize(using = DoubleSerializer.class)
    private double shop_price;

    private int promote_price;
    private int remain;
    private int sale_num;
    private int quanlity;
    private int unit_sell;
    private String unit;
    private String unit_desc;

    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getIs_remain() {
        return is_remain;
    }

    public void setIs_remain(int is_remain) {
        this.is_remain = is_remain;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_rough() {
        return is_rough;
    }

    public void setIs_rough(int is_rough) {
        this.is_rough = is_rough;
    }

    public int getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(int is_promote) {
        this.is_promote = is_promote;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public int getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(int promote_price) {
        this.promote_price = promote_price;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getSale_num() {
        return sale_num;
    }

    public void setSale_num(int sale_num) {
        this.sale_num = sale_num;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getUnit_sell() {
        return unit_sell;
    }

    public void setUnit_sell(int unit_sell) {
        this.unit_sell = unit_sell;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_desc() {
        return unit_desc;
    }

    public void setUnit_desc(String unit_desc) {
        this.unit_desc = unit_desc;
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

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
