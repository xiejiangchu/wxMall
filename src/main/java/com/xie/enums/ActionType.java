package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum ActionType {
    信息修改(10),
    订单操作(20),
    支付操作(30);

    private Integer _value;

    ActionType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}
