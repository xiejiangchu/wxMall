package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Error;
import com.xie.dao.ErrorDao;
import com.xie.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xie on 17/5/13.
 */
@Service
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    private ErrorDao errorDao;

    @Override
    public PageInfo<Error> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Error> page = new PageInfo<Error>(errorDao.getAll());
        return page;
    }

    @Override
    public Error getById(int id) {
        return errorDao.getById(id);
    }

    @Override
    public int count() {
        return errorDao.count();
    }

    @Override
    public int insert(Error error) {
        return errorDao.insert(error);
    }

    @Override
    public int delete(Error error) {
        return errorDao.delete(error);
    }

    @Override
    public int delete(int id) {
        return errorDao.delete(id);
    }
}
