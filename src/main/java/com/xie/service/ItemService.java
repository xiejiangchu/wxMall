package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
public interface ItemService {

    Item getById(int id);

    Item getDetailById(int id);

    PageInfo<Item> getAll(int pageNum, int pageSize);

    PageInfo<Item> getAllAvailable(int pageNum, int pageSize);

    PageInfo<Item> search(String keywors, int pageNum, int pageSize);

    PageInfo<Item> getAllCanShow(int orderBy, int pageNum, int pageSize);

    PageInfo<Item> top(int pageNum, int pageSize);

    PageInfo<Item> last(int pageNum, int pageSize);

    PageInfo<Item> lastUpdated(int pageNum, int pageSize);

    PageInfo<Item> getByCategory(Integer cid1, Integer cid2, int pageNum, int pageSize);

    PageInfo<Item> getByCategory(Integer cid1, int pageNum, int pageSize);

    List<Item> getByCategoryWithoutPaginate(int cid1, int cid2);

    int count(boolean all);

    int countByCid1Cid2(int cid1, int cid2);

    int insert(Item item);

    int update(Item item);

    int online(int id, int spec);

    boolean isOnline(int id);

    int offline(int id, int is_online);

    int delete(Item item);

    int delete(int id);

    int softDelete(int id);
}
