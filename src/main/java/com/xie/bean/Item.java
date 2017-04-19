package com.xie.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Item implements Serializable {
    private int id;
    private String no;
    private String name;
    private int cid1;
    private int cid2;
    private int is_online;
    private String src;
    private String thumb;
    private String place;
    private String summary;
    private String notice;
    private String notice_color;
    private String description;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private List<Image> imageList;
    private List<ItemSpec> itemSpecList;

    private String cid1_name;
    private String cid2_name;

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

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<ItemSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public String getNotice_color() {
        return notice_color;
    }

    public void setNotice_color(String notice_color) {
        this.notice_color = notice_color;
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
}

