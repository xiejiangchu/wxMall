package com.xie.request;

import com.xie.bean.Item;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午6:59.
 */
public class ItemDto {

    private Item item;
    private List<Integer> masterImageSelected;
    private List<Integer> slaveImageSelected;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Integer> getMasterImageSelected() {
        return masterImageSelected;
    }

    public void setMasterImageSelected(List<Integer> masterImageSelected) {
        this.masterImageSelected = masterImageSelected;
    }

    public List<Integer> getSlaveImageSelected() {
        return slaveImageSelected;
    }

    public void setSlaveImageSelected(List<Integer> slaveImageSelected) {
        this.slaveImageSelected = slaveImageSelected;
    }
}
