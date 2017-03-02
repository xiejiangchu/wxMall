package com.xie.dao;

import com.xie.bean.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class ItemDao extends BaseDao {

    public Item getById(int id) {
        return this.sqlSession.selectOne("ItemMapper.getById", id);
    }


    public List<Item> getAll() {
        return this.sqlSession.selectList("ItemMapper.getAll");
    }

    public List<Item> getAllAvailable() {
        return this.sqlSession.selectList("ItemMapper.getAllAvailable");
    }

    public List<Item> getAllCanShow() {
        return this.sqlSession.selectList("ItemMapper.getAllCanShow");
    }

    public List<Item> getByCategory(Integer cid1, Integer cid2) {
        Map map = new HashMap<String, Object>();
        map.put("cid1", cid1);
        map.put("cid2", cid2);
        return this.sqlSession.selectList("ItemMapper.getByCategory", map);
    }

    public List<Item> getByCategory(Integer cid1) {
        Map map = new HashMap<String, Object>();
        map.put("cid1", cid1);
        return this.sqlSession.selectList("ItemMapper.getByCategory", map);
    }

    public List<Item> search(String keywords) {
        Map map = new HashMap<String, Object>();
        map.put("keywords", keywords);
        return this.sqlSession.selectList("ItemMapper.search", map);
    }

    public List<Item> top() {
        return this.sqlSession.selectList("ItemMapper.top");
    }

    public int count(boolean all) {
        return this.sqlSession.selectOne("ItemMapper.count", all);
    }

    public int insert(Item item) {
        return this.sqlSession.insert("ItemMapper.insert", item);
    }

    public int update(Item item) {
        return this.sqlSession.update("ItemMapper.update", item);
    }

    public int delete(Item item) {
        Assert.notNull(item);
        Assert.isTrue(item.getId() > 0);
        return this.sqlSession.delete("ItemMapper.delete", item.getId());
    }

    public int delete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.delete("ItemMapper.delete", id);
    }

    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return this.sqlSession.update("ItemMapper.softDelete", id);
    }

    public int countByCid1Cid2(int cid1, int cid2) {
        Map map = new HashMap<String, Object>();
        map.put("cid1", cid1);
        map.put("cid2", cid2);
        return this.sqlSession.selectOne("ItemMapper.countByCid1Cid2", map);
    }

}