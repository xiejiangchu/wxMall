package com.xie.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xie.utils.DoubleSerializer;

import java.util.Date;

/**
 * @Author xie
 * @Date 17/2/24 下午6:22.
 */
public class OrderItem {
    private int id;
    private int oid;
    private String no;
    private String name;
    private int cid1;
    private int cid2;
    private int gid;
    private int spec;
    private int amount;
    @JsonSerialize(using = DoubleSerializer.class)
    private double total;
    @JsonSerialize(using = DoubleSerializer.class)
    private double weight;
    @JsonSerialize(using = DoubleSerializer.class)
    private double market_price;
    @JsonSerialize(using = DoubleSerializer.class)
    private double shop_price;

    private int promote_price;
    private int quanlity;
    private String unit;
    private int unit_sell;
    private String unit_desc;
    private String src;
    private String thumb;
    private String place;
    private String summary;
    private String notice;
    private String description;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getUnit_sell() {
        return unit_sell;
    }

    public void setUnit_sell(int unit_sell) {
        this.unit_sell = unit_sell;
    }

    public String getUnit_desc() {
        return unit_desc;
    }

    public void setUnit_desc(String unit_desc) {
        this.unit_desc = unit_desc;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
