package com.xie.request;

import com.xie.bean.Item;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午6:59.
 */
public class ItemDto {

    private Item item;
    private List<Integer> images;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
