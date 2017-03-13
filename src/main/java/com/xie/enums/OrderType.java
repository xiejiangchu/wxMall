package com.xie.enums;

/**
 * @Author xie
 * @Date 17/2/27 下午3:14.
 */
public enum OrderType {
    所有(0),

    待支付(10),
    待发货(20),
    待收货(30),
    已完成(40),
    已取消(50);

    private Integer _value;

    OrderType(Integer value) {
        this._value = value;
    }

    public Integer value() {
        return _value;
    }

    public static String getTypeName(int code){
        switch (code){
            case 0:
                return "所有";
            case 10:
                return "待支付";
            case 20:
                return "待发货";
            case 30:
                return "待收货";
            case 40:
                return "已完成";
            case 50:
                return "已取消";
        }
        return "";
    }
}
