package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Goods implements Serializable {
    private Integer id;
    private String no;
    private String name;
    private Integer category_id1;
    private Integer category_id2;
    private Byte is_remain;
    private Byte is_online;
    private Byte is_active;
    private Byte is_rough;
    private Byte is_promote;
    private Byte is_delete;
    private Byte status;
    private Timestamp promote_end;
    private Integer order;
    private Double weight;
    private Double order_quantity;
    private Double max_quantity;
    private Double market_price;
    private Double shop_price;
    private Double promote_price;
    private Double remain;
    private Double sale_num;
    private Integer quanlity;
    private String unit;
    private String unit_sell;
    private String unitdesc;
    private String src;
    private String thumb;
    private String place;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Byte getIs_remain() {
        return this.is_remain;
    }

    public void setIs_remain(Byte is_remain) {
        this.is_remain = is_remain;
    }

    public Byte getIs_online() {
        return this.is_online;
    }

    public void setIs_online(Byte is_online) {
        this.is_online = is_online;
    }

    public Byte getIs_active() {
        return this.is_active;
    }

    public void setIs_active(Byte is_active) {
        this.is_active = is_active;
    }

    public Byte getIs_rough() {
        return this.is_rough;
    }

    public void setIs_rough(Byte is_rough) {
        this.is_rough = is_rough;
    }

    public Byte getIs_promote() {
        return this.is_promote;
    }

    public void setIs_promote(Byte is_promote) {
        this.is_promote = is_promote;
    }

    public Byte getIs_delete() {
        return this.is_delete;
    }

    public void setIs_delete(Byte is_delete) {
        this.is_delete = is_delete;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Timestamp getPromote_end() {
        return this.promote_end;
    }

    public void setPromote_end(Timestamp promote_end) {
        this.promote_end = promote_end;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getOrder_quantity() {
        return this.order_quantity;
    }

    public void setOrder_quantity(Double order_quantity) {
        this.order_quantity = order_quantity;
    }

    public Double getMax_quantity() {
        return this.max_quantity;
    }

    public void setMax_quantity(Double max_quantity) {
        this.max_quantity = max_quantity;
    }

    public Double getMarket_price() {
        return this.market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getShop_price() {
        return this.shop_price;
    }

    public void setShop_price(Double shop_price) {
        this.shop_price = shop_price;
    }

    public Double getPromote_price() {
        return this.promote_price;
    }

    public void setPromote_price(Double promote_price) {
        this.promote_price = promote_price;
    }

    public Double getRemain() {
        return this.remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public Double getSale_num() {
        return this.sale_num;
    }

    public void setSale_num(Double sale_num) {
        this.sale_num = sale_num;
    }

    public Integer getQuanlity() {
        return this.quanlity;
    }

    public void setQuanlity(Integer quanlity) {
        this.quanlity = quanlity;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit_sell() {
        return this.unit_sell;
    }

    public void setUnit_sell(String unit_sell) {
        this.unit_sell = unit_sell;
    }

    public String getUnitdesc() {
        return this.unitdesc;
    }

    public void setUnitdesc(String unitdesc) {
        this.unitdesc = unitdesc;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
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

