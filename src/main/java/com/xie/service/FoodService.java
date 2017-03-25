package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Food;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-25 下午12:27
 */
public interface FoodService {

    Food getById(int id);

    Food getByName(String name);

    List<Food> getByCid(int cid);

    List<Food> search(String name);

    PageInfo<Food> getAll(int pageNum, int pageSize);

    int insert(Food food);

    int update(Food food);

    int delete(Food food);

    int delete(int id);
}
