package com.xie.service;

import com.xie.bean.Category;

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

    int countCid2ByCid1(int cid1);


    int insert(Category category);

    int update(Category category);

    int delete(Category category);

    int delete(int id);
}
