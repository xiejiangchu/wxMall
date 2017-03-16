package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum OrderState {

    进行中(10),
    已取消(20),
    已完成(30),
    已删除(40),
    系统回收(50);

    private Integer _value;

    OrderState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}
