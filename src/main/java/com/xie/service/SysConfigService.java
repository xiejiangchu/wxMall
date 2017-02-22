package com.xie.service;

import com.xie.bean.SysConfig;

/**
 * @Author xie
 * @Date 17/2/22 下午7:39.
 */
public interface SysConfigService {

    SysConfig getById(int id);

    int insert(SysConfig user);

    int update(SysConfig user);

    int delete(SysConfig user);

    int delete(int id);
}
