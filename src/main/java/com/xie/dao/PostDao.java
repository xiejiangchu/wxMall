package com.xie.dao;

import com.xie.bean.Post;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 17/5/4.
 */
@Component
public class PostDao extends BaseDao {

    public List<Post> getAll(){
        return this.sqlSession.selectList("PostMapper.getAll");
    }

    public List<Post> getAllCanShow(){
        return this.sqlSession.selectList("PostMapper.getAllCanShow");
    }

    public Post getById(int id){
        return this.sqlSession.selectOne("PostMapper.getById", id);
    }

    public int count(boolean all){
        return this.sqlSession.selectOne("PostMapper.count", all);
    }

    public int insert(Post post){
        return this.sqlSession.insert("PostMapper.insert", post);
    }

    public int update(Post post){
        return this.sqlSession.update("PostMapper.update", post);
    }

    public int delete(Post post){
        Assert.isTrue(post !=null);
        return this.sqlSession.delete("PostMapper.delete", post.getId());
    }

    public int delete(int id){
        return this.sqlSession.delete("PostMapper.delete", id);
    }

    public int softDelete(int id){
        return this.sqlSession.update("PostMapper.softDelete", id);
    }

    public int offline(int id, int online) {
        Map map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("online", online);
        return this.sqlSession.update("PostMapper.offline", map);
    }
}
