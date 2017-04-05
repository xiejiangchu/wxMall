package com.xie.controller.api;

import com.xie.bean.Category;
import com.xie.request.CategoryDto;
import com.xie.response.BaseResponse;
import com.xie.service.CategoryService;
import com.xie.service.ImageFileService;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ImageFileService imageFileService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(categoryService.getAll(pageNum, pageSize));
    }

    @RequestMapping(value = "/getCategoryLevel1", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCategoryLevel1() {
        return BaseResponse.ok(categoryService.getCategoryLevel1());
    }

    @RequestMapping(value = "/getCid1", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getCid1(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return BaseResponse.ok(categoryService.getCid1(pageNum, pageSize));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        List<Integer> masterImageSelected = categoryDto.getMasterImageSelected();
        Category category = categoryDto.getCategory();
        if (null != masterImageSelected && masterImageSelected.size() > 0) {
            category.setThumb(imageFileService.getById(masterImageSelected.get(0)).getUri());
        }
        int result = categoryService.update(category);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@RequestBody CategoryDto categoryDto) {
        List<Integer> masterImageSelected = categoryDto.getMasterImageSelected();
        Category category = categoryDto.getCategory();
        if (null != masterImageSelected && masterImageSelected.size() > 0) {
            category.setThumb(imageFileService.getById(masterImageSelected.get(0)).getUri());
        }
        int result = categoryService.insert(category);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
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


    @RequestMapping(value = "/offline", method = RequestMethod.PUT)
    @ResponseBody
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse offline2(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "online") int online) {
        int result = categoryService.offline(id, online);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = categoryService.delete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
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
