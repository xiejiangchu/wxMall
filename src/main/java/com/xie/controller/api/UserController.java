package com.xie.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.xie.bean.User;
import com.xie.response.BaseResponse;
import com.xie.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xie on 16/11/24.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getUser(@PathVariable("id") int id) {
        return BaseResponse.ok(userService.getById(id));
    }


    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ResponseBody
    BaseResponse getAllUser(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(userService.getAllUsers(pageNum, pageSize));
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResponse postUser(@ModelAttribute User user) {
        userService.insert(user);
        return BaseResponse.ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResponse putUser(@PathVariable int id, @ModelAttribute User user) {
        int result = userService.update(user);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteUser(@PathVariable int id) {
        int result = userService.softDelete(id);
        if (result > 0) {
            return BaseResponse.ok();
        } else {
            return BaseResponse.fail();
        }
    }

    @RequestMapping(value = "/web/wechatapp/jscode2session", method = RequestMethod.POST)
    @ResponseBody
    public String getSessionByCode(@RequestBody String jsonStr, HttpServletRequest request) {
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        String code = (String) jsonObj.get("code");
        JSONObject wechatAppUserInfo = jsonObj.getJSONObject("wechatAppUserInfo");
        String encryptedData = (String) wechatAppUserInfo.get("encryptedData");
        String iv = (String) wechatAppUserInfo.get("iv");

//        WechatUserInfo wechatUserInfo = wechatAppManager.doOAuth(code, encryptedData, iv);
//        if (wechatUserInfo == null) {
//            return "微信小程序授权失败！！！";
//        }
//        HttpSession session = request.getSession(true);
//        User user = wechatUserInfo.getUser();
//        logger.debug("微信小程序用户 union id: {}, 对应车车用户{}", wechatUserInfo.getUnionid(), user.getId());
//        session.setAttribute(WebConstants.SESSION_KEY_USER, CacheUtil.doJacksonSerialize(user));
//        ClientTypeUtil.cacheClientType(request, ClientType.WE_CHAT_APP);
//        return session.getId();
        return "success";
    }
}
