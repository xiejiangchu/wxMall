package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Food;
import com.xie.dao.FoodDao;
import com.xie.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-25 下午1:01
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDao foodDao;

    @Override
    public Food getById(int id) {
        return foodDao.getById(id);
    }

    @Override
    public Food getByName(String name) {
        return foodDao.getByName(name);
    }

    @Override
    public List<Food> getByCid(int cid) {
        return foodDao.getByCid(cid);
    }

    @Override
    public List<Food> search(String name) {
        return foodDao.search(name);
    }

    @Override
    public PageInfo<Food> getAll(int pageNum, int pageSize) {
        PageInfo<Food> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> foodDao.getAll());
        return page;
    }

    @Override
    public int insert(Food food) {
        return foodDao.insert(food);
    }

    @Override
    public int update(Food food) {
        return foodDao.update(food);
    }

    @Override
    public int delete(Food food) {
        return foodDao.delete(food);
    }

    @Override
    public int delete(int id) {
        return foodDao.delete(id);
    }
}
