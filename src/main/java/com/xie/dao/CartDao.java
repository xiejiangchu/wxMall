package com.xie.dao;

import com.xie.bean.Cart;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xie
 * @Date 17/2/22 下午2:39.
 */
@Component
public class CartDao extends BaseDao {

    public List<Cart> getByUid(int uid) {
        return this.sqlSession.selectList("CartMapper.getByUid", uid);
    }

    public List<Cart> getByUidWithItem(int uid) {
        return this.sqlSession.selectList("CartMapper.getByUidWithItem", uid);
    }

    public int clear(int uid) {
        return this.sqlSession.delete("CartMapper.clear", uid);
    }


    public int insert(Cart cart) {
        this.sqlSession.insert("CartMapper.insert", cart);
        return cart.getId();
    }


    public int update(Cart cart) {
        return this.sqlSession.update("CartMapper.update", cart);
    }


    public int delete(Cart cart) {

        Assert.notNull(cart);
        Assert.isTrue(cart.getId() > 0);
        return this.sqlSession.delete("CartMapper.delete", cart.getId());
    }

    public int clearByUid(int uid) {
        return this.sqlSession.delete("CartMapper.clearByUid", uid);
    }

    public int deleteByGidAndSpec(int gid, int spec) {
        Map map = new HashMap<String, Object>();
        map.put("gid", gid);
        map.put("spec", spec);
        return this.sqlSession.delete("CartMapper.deleteByGidAndSpec", map);
    }


    public int delete(int id) {
        return this.sqlSession.delete("CartMapper.delete", id);
    }

    public int saveOrUpdate(int uid, int gid, int spec, int amount) {
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setGid(gid);
        cart.setSpec(spec);
        cart.setAmount(amount);
        return this.sqlSession.insert("CartMapper.saveOrUpdate", cart);
    }
}
