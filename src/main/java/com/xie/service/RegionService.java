package com.xie.service;

import com.xie.bean.Region;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-26 下午12:00
 */
public interface RegionService {

    Region getById(int id);

    Region getByName();
}
