package com.xie.service.impl;

import com.xie.bean.SysConfig;
import com.xie.dao.SysConfigDao;
import com.xie.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午7:40.
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public SysConfig getById(int id) {
        return sysConfigDao.getById(id);
    }

    @Override
    public List<SysConfig> getAll() {
        return sysConfigDao.getAll();
    }

    @Override
    public int insert(SysConfig sysConfig) {
        return sysConfigDao.insert(sysConfig);
    }

    @Override
    public int update(SysConfig sysConfig) {
        return sysConfigDao.update(sysConfig);
    }

    @Override
    public int delete(SysConfig sysConfig) {
        return sysConfigDao.delete(sysConfig);
    }

    @Override
    public int delete(int id) {
        return sysConfigDao.delete(id);
    }

}
