package com.xie.service.impl;

import com.xie.bean.CnArea;
import com.xie.dao.CnAreaDao;
import com.xie.service.CnAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-30 上午9:13
 */
@Service
public class CnAreaServiceImpl implements CnAreaService {

    @Autowired
    private CnAreaDao cnAreaDao;

    @Override
    public CnArea getById(int id) {
        return cnAreaDao.getById(id);
    }

    @Override
    public CnArea getByName(String name) {
        return cnAreaDao.getByName(name);
    }

    @Override
    public List<CnArea> getByPid(int pid) {
        return cnAreaDao.getByPid(pid);
    }

    @Override
    public List<CnArea> getByLevel(int level) {
        return cnAreaDao.getByLevel(level);
    }

    @Override
    public int insert(CnArea cnArea) {
        return cnAreaDao.insert(cnArea);
    }

    @Override
    public int update(CnArea cnArea) {
        return cnAreaDao.update(cnArea);
    }

    @Override
    public int delete(CnArea cnArea) {
        return cnAreaDao.delete(cnArea);
    }

    @Override
    public int delete(int id) {
        return cnAreaDao.delete(id);
    }
}
