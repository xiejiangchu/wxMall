package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;

/**
 * Created by xie on 16/11/24.
 */
public interface ItemService {
    Item getById(int id);

    PageInfo<Item> getAll(int pageNum, int pageSize);


    PageInfo<Item> getByCategory(int level1, int level2, int pageNum, int pageSize);
}
