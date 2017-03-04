package com.xie.dao;

import com.xie.bean.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:39.
 */
@Component
public class ImageDao extends BaseDao {

    public Image getById(int id) {
        return this.sqlSession.selectOne("ImageMapper.getById", id);
    }

    public List<Image> getAll() {
        return this.sqlSession.selectList("ImageMapper.getAll");
    }

    public List<Image> getByName(String name) {
        return this.sqlSession.selectList("ImageMapper.getByName", name);
    }


    public int insert(Image image) {
        this.sqlSession.insert("ImageMapper.insert", image);
        return image.getId();
    }

    public int count() {
        return this.sqlSession.selectOne("ImageMapper.count");
    }

    public int update(Image image) {
        return this.sqlSession.update("ImageMapper.update", image);
    }


    public int delete(Image image) {

        Assert.notNull(image);
        Assert.isTrue(image.getId() > 0);
        return this.sqlSession.delete("ImageMapper.delete", image.getId());
    }


    public int delete(int id) {
        return this.sqlSession.delete("ImageMapper.delete", id);
    }
}
