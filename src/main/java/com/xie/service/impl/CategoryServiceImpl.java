package com.xie.service.impl;

import com.xie.bean.Category;
import com.xie.mapper.CategoryMapper;
import com.xie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return categoryMapper.getCategoryLevel1();
    }

    @Override
    public List<Category> getCategoryLevel2(int pid) {
        return categoryMapper.getCategoryLevel2(pid);
    }

    @Override
    public Category getById(int id) {
        return categoryMapper.getById(id);
    }
}
