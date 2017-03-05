package com.xie.response;

/**
 * @Author xie
 * @Date 17/3/5 上午11:51.
 */
public class OrderCountDto {

    private int order_pay;
    private int order_sending;
    private int order_receive;
    private int order_finish;

    public int getOrder_pay() {
        return order_pay;
    }

    public void setOrder_pay(int order_pay) {
        this.order_pay = order_pay;
    }

    public int getOrder_sending() {
        return order_sending;
    }

    public void setOrder_sending(int order_sending) {
        this.order_sending = order_sending;
    }

    public int getOrder_receive() {
        return order_receive;
    }

    public void setOrder_receive(int order_receive) {
        this.order_receive = order_receive;
    }

    public int getOrder_finish() {
        return order_finish;
    }

    public void setOrder_finish(int order_finish) {
        this.order_finish = order_finish;
    }
}
