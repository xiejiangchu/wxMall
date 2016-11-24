package com.xie.controller;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Goods;
import com.xie.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 16/11/24.
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "goods/{id}",method = RequestMethod.GET)
    @ResponseBody
    Goods getGoods(@PathVariable("id") String id){
        return goodsService.getGoodsById(id);
    }


    @RequestMapping(value = "goods",method = RequestMethod.GET)
    @ResponseBody
    PageInfo<Goods> getAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        return goodsService.getAllGoods(pageNum,pageSize);
    }
}
