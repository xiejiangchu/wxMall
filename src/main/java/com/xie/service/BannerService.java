package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Banner;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
public interface BannerService {

    PageInfo<Banner> getAll(int pageNum, int pageSize);

    List<Banner> getAllCanShow();

    Banner getById(int id);

    int offline(int id,int online);

    int count(boolean all);

    int insert(Banner address);

    int update(Banner address);

    int delete(Banner address);

    int delete(int id);

    int softDelete(int id);
}
