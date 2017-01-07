package com.xie.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xie on 17/1/7.
 */
public class Category implements Serializable {

    private int id;
    private int pid;
    private String name;

    private int level;
    private int order;
    private int is_delete;
    private int is_recommend;

    private String pic_category;
    private String pic_path_big;
    private String pic_path_little;


    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getPic_category() {
        return pic_category;
    }

    public void setPic_category(String pic_category) {
        this.pic_category = pic_category;
    }

    public String getPic_path_big() {
        return pic_path_big;
    }

    public void setPic_path_big(String pic_path_big) {
        this.pic_path_big = pic_path_big;
    }

    public String getPic_path_little() {
        return pic_path_little;
    }

    public void setPic_path_little(String pic_path_little) {
        this.pic_path_little = pic_path_little;
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
