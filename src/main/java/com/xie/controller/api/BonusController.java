package com.xie.controller.api;

import com.xie.bean.Bonus;
import com.xie.response.BaseResponse;
import com.xie.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by xie on 17/1/7.
 */
@Controller
@RequestMapping(value = "/bonus")
public class BonusController extends BaseController {

    @Autowired
    BonusService bonusService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse get(@PathVariable("id") int id) {
        return BaseResponse.ok(bonusService.getById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse list(@RequestParam("type") int type,
                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                      HttpSession session) {
        return BaseResponse.ok(bonusService.getListByType(getUid(session), type, pageNum, pageSize));
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

    @RequestMapping(value = "/{uid}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse post(@PathVariable(value = "uid") int uid,
                             @RequestParam(value = "tid") int aid,
                             @RequestParam(value = "is_enable", defaultValue = "0") int is_enable,
                             @RequestParam(value = "begin", defaultValue = "2017-01-01") Date begin,
                             @RequestParam(value = "end", defaultValue = "2017-01-01") Date end) {
        int result = bonusService.insert(uid, aid, is_enable, begin, end);
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
