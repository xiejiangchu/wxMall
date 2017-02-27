package com.xie.service;

import com.xie.bean.Category;
import com.xie.bean.Image;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllCanShow();

    List<Category> getCategoryLevel1();

    List<Category> getCategoryLevel2(int pid);

    Category getById(int id);

    int insert(Category category);

    int update(Category category);

    int delete(Category category);

    int delete(int id);
}
