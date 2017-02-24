package com.xie.dao;

import com.xie.bean.ItemImage;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:39.
 */
@Component
public class ItemImageDao extends BaseDao {

    public ItemImage getById(int id) {
        return this.sqlSession.selectOne("ItemImageMapper.getById", id);
    }

    public List<ItemImage> getAll() {
        return this.sqlSession.selectList("ItemImageMapper.getAll");
    }


    public int insert(ItemImage image) {
        this.sqlSession.insert("ItemImageMapper.insert", image);
        return image.getId();
    }

    public int count() {
        return this.sqlSession.selectOne("ItemImageMapper.count");
    }

    public int update(ItemImage image) {
        return this.sqlSession.update("ItemImageMapper.update", image);
    }


    public int delete(ItemImage image) {

        Assert.notNull(image);
        Assert.isTrue(image.getId() > 0);
        return this.sqlSession.delete("ItemImageMapper.delete", image.getId());
    }


    public int delete(int id) {
        return this.sqlSession.delete("ItemImageMapper.delete", id);
    }
}
