package com.xie.dao;

import com.xie.bean.Food;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-25 下午12:26
 */
@Component
public class FoodDao extends BaseDao {

    public Food getById(int id) {
        return this.sqlSession.selectOne("FoodMapper.getById", id);
    }

    public Food getByName(String name) {
        return this.sqlSession.selectOne("FoodMapper.getByName", name);
    }

    public List<Food> search(String name) {
        return this.sqlSession.selectList("FoodMapper.search", name);
    }

    public List<Food> getByCid(int cid) {
        return this.sqlSession.selectList("FoodMapper.getByCid", cid);
    }

    public List<Food> getAll() {
        return this.sqlSession.selectList("FoodMapper.getAll");
    }

    public int insert(Food food) {
        return this.sqlSession.insert("FoodMapper.update", food);
    }

    public int update(Food food) {
        return this.sqlSession.update("FoodMapper.update", food);
    }

    public int delete(Food food) {
        Assert.notNull(food);
        Assert.isTrue(food.getId() > 0);
        return this.sqlSession.delete("FoodMapper.delete", food.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("FoodMapper.delete", id);
    }
}
