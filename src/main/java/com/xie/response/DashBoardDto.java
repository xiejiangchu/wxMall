package com.xie.response;

import com.xie.bean.Item;

import java.util.List;

/**
 * @Author xie
 * @Date 17/3/4 上午9:50.
 */
public class DashBoardDto {

    private int user_count;
    private int item_count;
    private int order_count;
    private int Order_count_1;

    private int item_cart_count;
    private int item_add_list;

    private List<Item> last;
    private List<Item> lastUpdated;


    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getOrder_count_1() {
        return Order_count_1;
    }

    public void setOrder_count_1(int order_count_1) {
        Order_count_1 = order_count_1;
    }

    public int getItem_cart_count() {
        return item_cart_count;
    }

    public void setItem_cart_count(int item_cart_count) {
        this.item_cart_count = item_cart_count;
    }

    public int getItem_add_list() {
        return item_add_list;
    }

    public void setItem_add_list(int item_add_list) {
        this.item_add_list = item_add_list;
    }

    public List<Item> getLast() {
        return last;
    }

    public void setLast(List<Item> last) {
        this.last = last;
    }

    public List<Item> getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(List<Item> lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
