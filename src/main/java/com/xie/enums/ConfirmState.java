package com.xie.enums;

/**
 * @Author xie
 * @Date 17/1/22 下午2:22.
 */
public enum ConfirmState {

    已确认(10),
    待确认(20);

    private Integer _value;

    ConfirmState(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }
}
