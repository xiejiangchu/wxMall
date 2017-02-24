package com.xie.service;

import com.xie.bean.ItemImage;
import com.xie.bean.ItemSpec;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:02.
 */
public interface ItemImageService {
    ItemImage getById(int id);

    int insert(ItemImage itemImage);

    int insert(Integer iid,Integer imgid);

    int update(ItemImage itemImage);

    int delete(ItemImage itemImage);

    int delete(int id);
}
