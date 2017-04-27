package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;
import com.xie.dao.ItemDao;
import com.xie.enums.ItemOrderByType;
import com.xie.service.ItemImageService;
import com.xie.service.ItemService;
import com.xie.service.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */
@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private ItemSpecService itemSpecService;

    @Override
    public Item getById(int id) {
        return itemDao.getById(id);
    }

    @Override
    public Item getDetailById(int id) {
        Item item = itemDao.getByIdAdmin(id);
        if (null != item) {
            item.setImageList(itemImageService.getByIid(id));
            item.setItemSpecList(itemSpecService.getOnlineByGid(item.getId()));
        }
        return item;
    }

    @Override
    public PageInfo<Item> getAllAvailable(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.getAllAvailable());
        return page;
    }

    @Override
    public PageInfo<Item> getAllCanShow(int orderBy, int pageNum, int pageSize) {
        String orderby = null;
        if (orderBy == ItemOrderByType.推荐排序.value()) {
            orderby = "sort DESC";
        } else if (orderBy == ItemOrderByType.按商品名称字典正序.value()) {
            orderby = "name DESC";
        } else if (orderBy == ItemOrderByType.按商品名称字典反序.value()) {
            orderby = "name ASC";
        } else if (orderBy == ItemOrderByType.按商品类别由低到高.value()) {
            orderby = "cid1 ASC,cid2 ASC";
        } else if (orderBy == ItemOrderByType.按商品类别由高到低.value()) {
            orderby = "cid1 DESC,cid2 DESC";
        } else if (orderBy == ItemOrderByType.按上架时间由新到旧.value()) {
            orderby = "created_at DESC";
        } else if (orderBy == ItemOrderByType.按上架时间由旧到新.value()) {
            orderby = "created_at ASC";
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.getAllCanShow(orderby));
        List<Item> list = page.getList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setItemSpecList(itemSpecService.getOnlineByGid(list.get(i).getId()));
        }
        return page;
    }

    @Override
    public PageInfo<Item> search(String keywors, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.search(keywors));
        return page;
    }

    @Override
    public PageInfo<Item> top(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.top());
        return page;
    }

    @Override
    public PageInfo<Item> last(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.last());
        return page;
    }

    @Override
    public PageInfo<Item> lastUpdated(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemDao.lastUpdated());
        return page;
    }

    @Override
    public PageInfo<Item> getByCategory(Integer cid1, Integer cid2, int pageNum, int pageSize) {
        PageInfo<Item> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> itemDao.getByCategory(cid1, cid2));
        return page;
    }

    @Override
    public PageInfo<Item> getByCategory(Integer cid1, int pageNum, int pageSize) {
        PageInfo<Item> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> itemDao.getByCategory(cid1, null));
        return page;
    }

    @Override
    public List<Item> getByCategoryWithoutPaginate(int cid1, int cid2) {
        return itemDao.getByCategory(cid1, cid2);
    }

    @Override
    public PageInfo<Item> getAll(int pageNum, int pageSize) {
        PageInfo<Item> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> itemDao.getAll());
        return page;
    }

    @Override
    public int countByCid1Cid2(int cid1, int cid2) {
        return itemDao.countByCid1Cid2(cid1, cid2);
    }

    @Override
    public int count(boolean all) {
        return itemDao.count(all);
    }

    @Override
    public int insert(Item item) {
        return itemDao.insert(item);
    }

    @Override
    public int update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public int online(int id, int spec) {
        return itemDao.online(id, spec);
    }

    @Override
    public boolean isOnline(int id) {
        int result = itemDao.isOnline(id);
        return result > 0;
    }

    @Override
    public int offline(int id, int is_online) {
        return itemDao.offline(id, is_online);
    }

    @Override
    public int delete(Item item) {
        Assert.notNull(item);
        Assert.isTrue(item.getId() > 0);
        return itemDao.delete(item.getId());
    }

    @Override
    public int delete(int id) {
        Assert.isTrue(id > 0);
        return itemDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return itemDao.softDelete(id);
    }

}
