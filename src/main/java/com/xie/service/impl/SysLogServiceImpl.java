package com.xie.service.impl;

import com.xie.bean.SysLog;
import com.xie.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午9:33.
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogService sysLogService;

    @Override
    public SysLog getById(int id) {
        return sysLogService.getById(id);
    }

    @Override
    public List<SysLog> getBySid(int sid) {
        return sysLogService.getBySid(sid);
    }

    @Override
    public int countBySid(int sid) {
        return sysLogService.countBySid(sid);
    }

    @Override
    public int count(int sid) {
        return sysLogService.count(sid);
    }

    @Override
    public int insert(SysLog sysLog) {
        return sysLogService.insert(sysLog);
    }

    @Override
    public int update(SysLog sysLog) {
        return sysLogService.update(sysLog);
    }

    @Override
    public int delete(SysLog sysLog) {
        return sysLogService.delete(sysLog);
    }

    @Override
    public int delete(int id) {
        return sysLogService.delete(id);
    }

    @Override
    public int deleteBySid(int sid) {
        return sysLogService.deleteBySid(sid);
    }
}
