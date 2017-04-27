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

    public Item getByIdAdmin(int id){
        return this.sqlSession.selectOne("ItemMapper.getByIdAdmin", id);
    }


    public List<Item> getAll() {
        return this.sqlSession.selectList("ItemMapper.getAll");
    }

    public List<Item> getAllAvailable() {
        return this.sqlSession.selectList("ItemMapper.getAllAvailable");
    }

    public List<Item> getAllCanShow(String orderBy) {
        Map map = new HashMap<String, Object>();
        map.put("orderBy", orderBy);
        return this.sqlSession.selectList("ItemMapper.getAllCanShow", map);
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

    public int isOnline(int id){
        return this.sqlSession.selectOne("ItemMapper.isOnline", id);
    }

    public List<Item> top() {
        return this.sqlSession.selectList("ItemMapper.top");
    }

    public List<Item> last() {
        return this.sqlSession.selectList("ItemMapper.last");
    }

    public List<Item> lastUpdated() {
        return this.sqlSession.selectList("ItemMapper.lastUpdated");
    }

    public int count(boolean all) {
        Map map = new HashMap<String, Object>();
        map.put("all", all);
        return this.sqlSession.selectOne("ItemMapper.count", map);
    }

    public int insert(Item item) {
        this.sqlSession.insert("ItemMapper.insert", item);
        return item.getId();
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

    public int offline(int id, int is_online) {
        Map map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("is_online", is_online);
        return this.sqlSession.update("ItemMapper.offline", map);
    }

    public int countByCid1Cid2(int cid1, int cid2) {
        Map map = new HashMap<String, Object>();
        map.put("cid1", cid1);
        map.put("cid2", cid2);
        return this.sqlSession.selectOne("ItemMapper.countByCid1Cid2", map);
    }


    public int online(int id, int spec) {
        Map map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("spec", spec);
        return this.sqlSession.selectOne("ItemMapper.online", map);
    }

}