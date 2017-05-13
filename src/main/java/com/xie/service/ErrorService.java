package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Error;

/**
 * Created by xie on 17/1/7.
 */
public interface ErrorService {

    PageInfo<Error> getAll(int pageNum, int pageSize);

    Error getById(int id);

    int count();

    int insert(Error error);

    int delete(Error error);

    int delete(int id);
}
