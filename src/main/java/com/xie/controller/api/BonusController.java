package com.xie.controller.api;

import com.xie.bean.Bonus;
import com.xie.response.BaseResponse;
import com.xie.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/bonus")
public class BonusController {

    @Autowired
    BonusService bonusService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bonusService.getById(id));
    }

    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@PathVariable("uid") int uid) {
        return BaseResponse.ok(bonusService.getAllByUid(uid));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@ModelAttribute Bonus bonus) {
        int result = bonusService.insert(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse put(@PathVariable("id") int id, @ModelAttribute Bonus bonus) {
        int result = bonusService.update(bonus);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResponse delete(@PathVariable("id") int id) {
        int result = bonusService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }

    }
}
