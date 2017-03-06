package com.xie.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public class Category implements Serializable {

    private int id;
    private int pid;
    private String name;
    private int level;
    private int sort;
    private int is_delete;
    private String thumb;
    private Date created_at;
    private Date updated_at;
    private List<Category> cid2List;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    public List<Category> getCid2List() {
        return cid2List;
    }

    public void setCid2List(List<Category> cid2List) {
        this.cid2List = cid2List;
    }
}
