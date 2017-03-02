package com.xie.service;

import com.xie.bean.Image;
import com.xie.bean.ItemImage;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:02.
 */
public interface ItemImageService {
    ItemImage getById(int id);

    List<Image> getByIid(int iid);

    int insert(ItemImage itemImage);

    int insert(Integer iid,Integer imgid);

    int update(ItemImage itemImage);

    int delete(ItemImage itemImage);

    int delete(int id);
}
