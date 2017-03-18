package com.xie.controller.api;

import com.xie.bean.User;
import com.xie.response.BaseResponse;
import com.xie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/2/23 下午12:46.
 */
public class BaseController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public User getUser(String sessionId) {
        return userService.getBySessionId(sessionId);
    }

    public int getUid(String sessionId) {
        return userService.getBySessionId(sessionId).getId();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    private BaseResponse exceptionHandler(IllegalArgumentException exception) {
        return BaseResponse.fail(exception.getMessage());
    }
}
