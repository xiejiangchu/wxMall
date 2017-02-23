package com.xie.controller.api;

import com.xie.bean.BonusType;
import com.xie.response.BaseResponse;
import com.xie.service.BonusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/bonusType")
public class BonusTypeController extends BaseController{

    @Autowired
    BonusTypeService bonusTypeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bonusTypeService.getById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list() {
        return BaseResponse.ok(bonusTypeService.getAllEnabled());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse all() {
        return BaseResponse.ok(bonusTypeService.getAll());
    }


    @RequestMapping(value = "/count/{gid}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse count(@PathVariable("gid") int gid) {
        return BaseResponse.ok(bonusTypeService.countByGid(gid));
    }

    @RequestMapping(value = "/getAllByGid", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam("gid") Integer gid) {
        return BaseResponse.ok(bonusTypeService.getAllByGid(gid));
    }

    @RequestMapping(value = "/list/{cid1}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@PathVariable("cid1") int cid1) {
        return BaseResponse.ok(bonusTypeService.getAllByCid(cid1, null));
    }

    @RequestMapping(value = "/list/{cid1}/{cid2}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@PathVariable("cid1") int cid1, @PathVariable("cid2") int cid2) {
        return BaseResponse.ok(bonusTypeService.getAllByCid(cid1, cid2));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute BonusType bonus) {
        int result = bonusTypeService.insert(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute BonusType bonus) {
        int result = bonusTypeService.update(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = bonusTypeService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}
