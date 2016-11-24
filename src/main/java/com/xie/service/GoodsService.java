package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Goods;

/**
 * Created by xie on 16/11/24.
 */
public interface GoodsService {
    Goods getGoodsById(String id);

    PageInfo<Goods> getAllGoods(int pageNum, int pageSize);
}
