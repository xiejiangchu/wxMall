package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
public interface ItemService {

    Item getById(int id);

    List<Item> getAll();

    PageInfo<Item> getAllAvailable(int pageNum, int pageSize);

    PageInfo<Item> getAllCanShow(int pageNum, int pageSize);

    PageInfo<Item> top(int pageNum, int pageSize);

    PageInfo<Item> getByCategory(Integer level1, Integer level2, int pageNum, int pageSize);

    PageInfo<Item> getByCategory(Integer level1, int pageNum, int pageSize);

    int count(boolean all);

    int insert(Item item);

    int update(Item item);

    int delete(Item item);

    int delete(int id);

    int softDelete(int id);
}
