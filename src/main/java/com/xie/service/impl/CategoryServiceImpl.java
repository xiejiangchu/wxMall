package com.xie.service.impl;

import com.xie.bean.Category;
import com.xie.mapper.CategoryMapper;
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
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAll() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public List<Category> getAllCanShow() {
        return categoryMapper.getAllCategoryCanShow();
    }

    @Override
    public List<Category> getCategoryLevel1() {
        List<Category> list = categoryMapper.getCategoryLevel1();
        if (null != list) {
            list.get(0).setCid2List(getCategoryLevel2(list.get(0).getId()));
        }
        return list;
    }

    @Override
    public List<Category> getCategoryLevel2(int pid) {
        return categoryMapper.getCategoryLevel2(pid);
    }

    @Override
    public Category getById(int id) {
        return categoryMapper.getById(id);
    }

    @Override
    public int countCid2ByCid1(int cid1) {
        return categoryMapper.countCid2ByCid1(cid1);
    }


    @Override
    public int insert(Category category) {
        // TODO: 17/2/26
        return 0;
    }

    @Override
    public int update(Category category) {
        // TODO: 17/2/26
        return 0;
    }

    @Override
    public int delete(Category category) {
        Assert.notNull(category);
        return categoryMapper.delete(category.getId());
    }

    @Override
    public int delete(int id) {
        return categoryMapper.delete(id);
    }
}
