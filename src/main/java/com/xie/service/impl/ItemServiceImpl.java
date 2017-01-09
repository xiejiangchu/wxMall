package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Item;
import com.xie.dao.ItemDao;
import com.xie.mapper.ItemMapper;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xie on 16/11/24.
 */
@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDao itemDao;

    @Override
    public Item getById(int id) {
        return itemDao.getById(id);
    }

    @Override
    public PageInfo<Item> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<Item>(itemMapper.getAll());
        return page;
    }

    @Override
    public PageInfo<Item> getByCategory(int level1, int level2, int pageNum, int pageSize) {
        PageInfo<Item> page=PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()-> itemMapper.getByCategory(level1, level2));
        return page;
    }
}
