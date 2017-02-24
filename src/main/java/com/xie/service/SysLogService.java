package com.xie.service;

import com.xie.bean.SysLog;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午9:24.
 */
public interface SysLogService {

    SysLog getById(int id);

    List<SysLog> getBySid(int sid);

    int countBySid(int sid);

    int count(int sid);

    int insert(SysLog sysLog);

    int update(SysLog sysLog);

    int delete(SysLog sysLog);

    int delete(int id);

    int deleteBySid(int sid);
}
