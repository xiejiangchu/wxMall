package com.xie.response;

/**
 * Created by xie on 16/12/12.
 */
public class BaseResponse {
    private int code;
    private Object msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
