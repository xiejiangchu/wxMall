package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum ShipState {

    待配送(10),
    配送中(20),
    已配送(30);

    private Integer _value;

    ShipState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}
