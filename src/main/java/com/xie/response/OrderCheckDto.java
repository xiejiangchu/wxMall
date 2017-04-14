package com.xie.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xie.bean.Address;
import com.xie.bean.Cart;
import com.xie.bean.Payment;
import com.xie.utils.DoubleSerializer;

import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/3/12 下午3:20.
 */
public class OrderCheckDto {
    private Address address;
    private int bonusCount;
    private int point;

    @JsonSerialize(using = DoubleSerializer.class)
    private double totalAmount;
    private List<Cart> items;
    private Date date_start;
    private Date date_end;
    private String time_start;
    private String time_end;
    private List<Payment> payments;
    private int changed;
    private int point_rate;
    private int promoto_price;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public void setBonusCount(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    public List<Cart> getItems() {
        return items;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getPoint_rate() {
        return point_rate;
    }

    public void setPoint_rate(int point_rate) {
        this.point_rate = point_rate;
    }

    public int getPromoto_price() {
        return promoto_price;
    }

    public void setPromoto_price(int promoto_price) {
        this.promoto_price = promoto_price;
    }
}
