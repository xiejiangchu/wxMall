package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Category;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface CategoryService {

    PageInfo<Category> getAll(int pageNum, int pageSize);

    List<Category> getAllCanShow();

    List<Category> getCategoryLevel1();

    List<Category> getCategoryLevel2(int pid);

    PageInfo<Category> getCid1(int pageNum, int pageSize);

    Category getById(int id);

    int countCid2ByCid1(int cid1);

    int insert(Category category);

    int update(Category category);

    int offline(int id,int online);

    int delete(Category category);

    int delete(int id);
}
