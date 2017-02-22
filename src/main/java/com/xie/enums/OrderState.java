package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum OrderState {

    进行中(100),
    已取消(200),
    已完成(300),
    已删除(400),
    系统回收(500);

    private Integer _value;

    OrderState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}
