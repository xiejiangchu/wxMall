package com.xie.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private String NO;
    private int uid;
    private int confirmed;
    private int order_status;
    private int pay_status;
    private int ship_status;
    private int package_status;
    private double order_amount;
    private double order_weight;
    private double order_money;
    private double order_amount_real;
    private double order_money_real;
    private int bonus_id;
    private double bonus;
    private double point;
    private int payment;
    private Date send_date;
    private Date time_start;
    private Date time_end;
    private int address_id;
    private String mobile;
    private String receiver;
    private String city;
    private String district;
    private String road;
    private String address;
    private String message;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;
} 

