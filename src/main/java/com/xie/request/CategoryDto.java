package com.xie.request;

import com.xie.bean.Category;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午6:59.
 */
public class CategoryDto {

    private Category category;
    private List<Integer> masterImageSelected;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Integer> getMasterImageSelected() {
        return masterImageSelected;
    }

    public void setMasterImageSelected(List<Integer> masterImageSelected) {
        this.masterImageSelected = masterImageSelected;
    }
}
