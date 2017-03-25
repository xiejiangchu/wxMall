package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.CategoryFood;

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
public interface CategoryFoodService {

    CategoryFood getById(int id);

    CategoryFood getByName(String name);

    PageInfo<CategoryFood> getAll(int pageNum, int pageSize);

    int insert(CategoryFood categoryFood);

    int update(CategoryFood categoryFood);

    int delete(CategoryFood categoryFood);

    int delete(int id);
}
