package com.xie.dao;

import com.xie.bean.Category;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 下午1:43.
 */
@Component
public class CategoryDao extends BaseDao {

    public List<Category> getAllCategory(){
        return this.sqlSession.selectList("CategoryMapper.getAllCategory");
    }


    public List<Category> getAllCategoryCanShow(){
        return this.sqlSession.selectList("CategoryMapper.getAllCategoryCanShow");
    }


    public Category getById(int id){
        return this.sqlSession.selectOne("CategoryMapper.getById",id);
    }



    public List<Category> getCategoryLevel1(){
        return this.sqlSession.selectList("CategoryMapper.getCategoryLevel1");
    }


    public List<Category> getCategoryLevel2(int pid){
        return this.sqlSession.selectList("CategoryMapper.getCategoryLevel2",pid);
    }


    public int countCid2ByCid1(int cid1){
        return this.sqlSession.selectOne("CategoryMapper.countCid2ByCid1",cid1);
    }

    public int delete(int id){
        return this.sqlSession.delete("CategoryMapper.delete",id);
    }

    public int softDelete(int id){
        return this.sqlSession.update("CategoryMapper.softDelete",id);
    }

    public int insert(Category category) {
        return this.sqlSession.insert("CategoryMapper.insert", category);
    }

    public int update(Category category) {
        return this.sqlSession.update("CategoryMapper.update", category);
    }


}
