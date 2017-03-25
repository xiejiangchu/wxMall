package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.CategoryFood;
import com.xie.dao.CategoryFoodDao;
import com.xie.service.CategoryFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-25 下午12:59
 */
@Service
public class CategoryFoodServiceImpl implements CategoryFoodService {

    @Autowired
    private CategoryFoodDao categoryFoodDao;

    @Override
    public CategoryFood getById(int id) {
        return categoryFoodDao.getById(id);
    }

    @Override
    public CategoryFood getByName(String name) {
        return categoryFoodDao.getByName(name);
    }

    @Override
    public PageInfo<CategoryFood> getAll(int pageNum, int pageSize) {
        PageInfo<CategoryFood> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> categoryFoodDao.getAll());
        return page;
    }

    @Override
    public int insert(CategoryFood categoryFood) {
        return categoryFoodDao.insert(categoryFood);
    }

    @Override
    public int update(CategoryFood categoryFood) {
        return categoryFoodDao.update(categoryFood);
    }

    @Override
    public int delete(CategoryFood categoryFood) {
        return categoryFoodDao.delete(categoryFood);
    }

    @Override
    public int delete(int id) {
        return categoryFoodDao.delete(id);
    }
}
