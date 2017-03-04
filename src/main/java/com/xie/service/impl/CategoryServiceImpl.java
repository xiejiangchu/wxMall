package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Category;
import com.xie.dao.CategoryDao;
import com.xie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.getAllCategory();
    }

    @Override
    public List<Category> getAllCanShow() {
        return categoryDao.getAllCategoryCanShow();
    }

    @Override
    public List<Category> getCategoryLevel1() {
        List<Category> list = categoryDao.getCategoryLevel1();
        if (null != list) {
            list.get(0).setCid2List(getCategoryLevel2(list.get(0).getId()));
        }
        return list;
    }

    @Override
    public List<Category> getCategoryLevel2(int pid) {
        return categoryDao.getCategoryLevel2(pid);
    }

    @Override
    public PageInfo<Category> getCid1(int pageNum, int pageSize) {
        PageInfo<Category> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> categoryDao.getCategoryLevel1());
        return page;
    }

    @Override
    public Category getById(int id) {
        return categoryDao.getById(id);
    }

    @Override
    public int countCid2ByCid1(int cid1) {
        return categoryDao.countCid2ByCid1(cid1);
    }


    @Override
    public int insert(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public int update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public int delete(Category category) {
        Assert.notNull(category);
        return categoryDao.delete(category.getId());
    }

    @Override
    public int delete(int id) {
        return categoryDao.delete(id);
    }
}
