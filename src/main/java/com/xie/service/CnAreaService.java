package com.xie.service;

import com.xie.bean.CnArea;

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
public interface CnAreaService {

    public CnArea getById(int id);

    public CnArea getByName(String name);

    public List<CnArea> getByPid(int pid);

    public List<CnArea> getByLevel(int level);

    public int insert(CnArea cnArea);

    public int update(CnArea cnArea);

    public int delete(CnArea cnArea);

    public int delete(int id);
}
