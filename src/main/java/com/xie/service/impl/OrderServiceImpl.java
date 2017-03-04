package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Address;
import com.xie.bean.Cart;
import com.xie.bean.Order;
import com.xie.bean.OrderItem;
import com.xie.dao.OrderDao;
import com.xie.enums.*;
import com.xie.service.AddressService;
import com.xie.service.CartService;
import com.xie.service.OrderItemService;
import com.xie.service.OrderService;
import com.xie.utils.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/1/23 上午10:36.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public Order getById(int id) {
        return orderDao.getById(id);
    }

    @Override
    public PageInfo<Order> getAllByUid(int uid, int pageNum, int pageSize) {
        PageInfo<Order> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> orderDao.getAllByUid(uid));
        return page;
    }

    @Override
    public PageInfo<Order> getAll(int pageNum, int pageSize) {
        PageInfo<Order> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> orderDao.getAll());
        return page;
    }

    @Override
    public PageInfo<Order> getByType(int type, int pageNum, int pageSize) {
        PageInfo<Order> page = null;
        int uid = 2;
        if (OrderType.待支付.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, OrderState.进行中.value(), PayState.未支付.value(), ShipState.待配送.value(), PackageState.未打包.value()));
        } else if (OrderType.待发货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, OrderState.进行中.value(), PayState.已支付.value(), ShipState.待配送.value(), null));
        } else if (OrderType.待收货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, OrderState.进行中.value(), PayState.已支付.value(), ShipState.配送中.value(), PackageState.已打包.value()));
        } else if (OrderType.已完成.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, OrderState.已完成.value(), PayState.已支付.value(), ShipState.已配送.value(), PackageState.已打包.value()));
            ;
        }
        return page;
    }

    @Override
    public int insert(Order order) {
        order.setNO(StringUtils.generateOrderNo());

        return orderDao.insert(order);
    }

    @Override
    public int submit(int uid, int aid, int bid, int pid, String message) {
        List<Cart> cartList = cartService.getByUidWithItem(uid);
        List<OrderItem> orderItems = new ArrayList<>();
        Address address = addressService.getById(aid);
        Order order = new Order();

        order.setNO(StringUtils.generateOrderNo());
        order.setUid(uid);

        //status
        order.setConfirmed(ConfirmState.待确认.value());
        order.setOrder_status(OrderState.进行中.value());
        order.setPay_status(PayState.未支付.value());
        order.setShip_status(ShipState.待配送.value());
        order.setPackage_status(PackageState.未打包.value());

        double order_amount = 0;
        double order_weight = 0;
        double order_money = 0;
        for (int i = 0; i < cartList.size(); i++) {
            order_amount += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getUnit_sell();
            order_weight += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getWeight();
            order_money += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getShop_price();
        }
        order.setOrder_amount(order_amount);
        order.setOrder_weight(order_weight);
        order.setOrder_money(order_money);

        //point
        order.setPoint(order_money * 100);

        //地址操作
        order.setAddress_id(address.getId());
        order.setCity(address.getCity());
        order.setDistrict(address.getDistrict());
        order.setMobile(address.getMobile());
        order.setReceiver(address.getReceiver());
        order.setRoad(address.getRoad());
        order.setAddress(address.getAddress());

        //message
        order.setMessage(message);

        //sendDate
        order.setSend_date(new java.sql.Date(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(9).toDate().getTime()));
        order.setTime_start(new Time(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(9).toDate().getTime()));
        order.setTime_end(new Time(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(10).toDate().getTime()));
        int oid = orderDao.insert(order);

        //插入order详情
        for (int i = 0; i < cartList.size(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(oid);
            BeanUtils.copyProperties(cartList.get(i), orderItem, "created_at", "updated_at", "deleted_at");
            BeanUtils.copyProperties(cartList.get(i).getItem(), orderItem, "created_at", "updated_at", "deleted_at");
            BeanUtils.copyProperties(cartList.get(i).getItemSpec(), orderItem, "created_at", "updated_at", "deleted_at");

            orderItems.add(orderItem);
        }
        orderItemService.insert(orderItems);

        //删除购物车
        cartService.clear(uid);

        return oid;
    }

    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int delete(Order order) {
        Assert.notNull(order);
        return orderDao.delete(order.getId());
    }

    @Override
    public int delete(int id) {
        return orderDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        return orderDao.softDelete(id);
    }

    @Override
    public int countByUid(int uid) {
        return orderDao.countByUid(uid);
    }

    @Override
    public int count(Date start, Date end) {
        return orderDao.count(start,end);
    }
}
