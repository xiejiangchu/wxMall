package com.xie.dao;

import com.xie.bean.ItemSpec;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class ItemSpecDao extends BaseDao {

    public ItemSpec getById(int id) {
        return this.sqlSession.selectOne("ItemSpecMapper.getById", id);
    }


    public List<ItemSpec> getAllByGid() {
        return this.sqlSession.selectList("ItemSpecMapper.getAllByGid");
    }


    public int insert(ItemSpec item) {
        return this.sqlSession.insert("ItemSpecMapper.insert", item);
    }

    public int update(ItemSpec item) {
        return this.sqlSession.update("ItemSpecMapper.update", item);
    }

    public int delete(ItemSpec item) {
        Assert.notNull(item);
        Assert.isTrue(item.getId() > 0);
        return this.sqlSession.delete("ItemSpecMapper.delete", item.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("ItemSpecMapper.delete", id);
    }

    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.update("ItemSpecMapper.softDelete", id);
    }

}