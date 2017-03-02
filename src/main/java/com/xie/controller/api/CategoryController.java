package com.xie.controller.api;

import com.xie.bean.Category;
import com.xie.response.BaseResponse;
import com.xie.service.CategoryService;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getAll() {
        return BaseResponse.ok(categoryService.getAll());
    }

    @RequestMapping(value = "/getCategoryLevel1", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryLevel1() {
        return BaseResponse.ok(categoryService.getCategoryLevel1());
    }


    @RequestMapping(value = "/getCategoryLevel2/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryLevel2(@PathVariable("pid") int pid) {
        return BaseResponse.ok(categoryService.getCategoryLevel2(pid));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryDetail(@PathVariable("id") int id) {
        return BaseResponse.ok(categoryService.getById(id));
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse clear() {
        List<Category> categoryList = categoryService.getCategoryLevel1();
        for (int i = 0; i < categoryList.size(); i++) {
            Category category1 = categoryList.get(i);
            List<Category> categoryList1 = categoryService.getCategoryLevel2(category1.getId());
            for (int j = 0; j < categoryList1.size(); j++) {
                int count = itemService.countByCid1Cid2(category1.getId(), categoryList1.get(j).getId());
                System.out.println("result=" + category1.getId() + " -- " + categoryList1.get(j).getId() + " -- " + count);
                if (count == 0) {
                    categoryService.delete(categoryList1.get(j));
                }
            }
            if (categoryService.countCid2ByCid1(category1.getId()) == 0) {
                categoryService.delete(category1);
            }

        }
        return BaseResponse.ok();
    }
}
