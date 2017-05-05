package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Post;

/**
 * Created by xie on 17/1/7.
 */
public interface PostService {

    PageInfo<Post> getAll(int pageNum, int pageSize);

    PageInfo<Post> getAllCanShow(int pageNum, int pageSize);

    Post getById(int id);

    int count(boolean all);

    int insert(Post post);

    int update(Post post);

    int delete(Post post);

    int delete(int id);

    int softDelete(int id);

    int offline(int id,int online);
}
