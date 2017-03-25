package com.xie.dao;

import com.xie.bean.CategoryFood;
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
public class CategoryFoodDao extends BaseDao {

    public CategoryFood getById(int id) {
        return this.sqlSession.selectOne("CategoryFoodMapper.getById", id);
    }

    public CategoryFood getByName(String name) {
        return this.sqlSession.selectOne("CategoryFoodMapper.getByName", name);
    }

    public List<CategoryFood> getAll() {
        return this.sqlSession.selectList("CategoryFoodMapper.getAll");
    }

    public int insert(CategoryFood categoryFood) {
        return this.sqlSession.insert("CategoryFoodMapper.update", categoryFood);
    }

    public int update(CategoryFood categoryFood) {
        return this.sqlSession.update("CategoryFoodMapper.update", categoryFood);
    }

    public int delete(CategoryFood categoryFood) {
        Assert.notNull(categoryFood);
        Assert.isTrue(categoryFood.getId() > 0);
        return this.sqlSession.delete("CategoryFoodMapper.delete", categoryFood.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("CategoryFoodMapper.delete", id);
    }
}
