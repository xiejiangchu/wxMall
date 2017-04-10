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

    int check(int iid,int imgid);

    int insert(int iid, int imgid, int type);

    int update(ItemImage itemImage);

    int delete(ItemImage itemImage);

    int delete(int id);

    int delete(List<Integer> ids);

    int deleteByIid(int iid);
}
