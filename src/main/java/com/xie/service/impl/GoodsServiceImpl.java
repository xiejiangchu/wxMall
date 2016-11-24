package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Goods;
import com.xie.mapper.GoodsMapper;
import com.xie.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xie on 16/11/24.
 */
@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(String id) {
        return goodsMapper.findById(id);
    }

    @Override
    public PageInfo<Goods> getAllGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Goods> page = new PageInfo<Goods>(goodsMapper.getAll());
        return page;
    }
}
