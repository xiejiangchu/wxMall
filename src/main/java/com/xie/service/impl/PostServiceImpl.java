package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Post;
import com.xie.dao.PostDao;
import com.xie.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xie on 17/5/4.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public PageInfo<Post> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Post> page = new PageInfo<Post>(postDao.getAll());
        return page;
    }

    @Override
    public PageInfo<Post> getAllCanShow(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Post> page = new PageInfo<Post>(postDao.getAllCanShow());
        return page;
    }

    @Override
    public Post getById(int id) {
        return postDao.getById(id);
    }

    @Override
    public int count(boolean all) {
        return postDao.count(all);
    }

    @Override
    public int insert(Post post) {
        return postDao.insert(post);
    }

    @Override
    public int update(Post post) {
        return postDao.update(post);
    }

    @Override
    public int delete(Post post) {
        return postDao.delete(post);
    }

    @Override
    public int delete(int id) {
        return postDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        return postDao.softDelete(id);
    }

    @Override
    public int offline(int id, int online) {
        return postDao.offline(id, online);
    }
}
