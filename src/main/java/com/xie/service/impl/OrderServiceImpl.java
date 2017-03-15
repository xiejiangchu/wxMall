package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.*;
import com.xie.dao.OrderDao;
import com.xie.enums.*;
import com.xie.response.OrderCheckDto;
import com.xie.response.OrderCountDto;
import com.xie.service.*;
import com.xie.utils.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @Autowired
    private BonusService bonusService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSpecService itemSpecService;

    @Override
    public Order getById(int id) {
        Order order = orderDao.getById(id);
        if (null != order) {
            order.setOrderItems(orderItemService.getByOid(order.getId()));
            if (order.getOrder_status() == OrderState.已取消.value()) {
                order.setStatus(OrderType.已取消.value());
                order.setStatusName(OrderType.getTypeName(OrderType.已取消.value()));
            } else if (order.getOrder_status() == OrderState.已完成.value()) {
                order.setStatus(OrderType.已完成.value());
                order.setStatusName(OrderType.getTypeName(OrderType.已完成.value()));
            } else if (order.getOrder_status() == OrderState.进行中.value()) {
                if (order.getPay_status() == PayState.未支付.value()) {
                    order.setStatus(OrderType.待支付.value());
                    order.setStatusName(OrderType.getTypeName(OrderType.待支付.value()));
                } else if (order.getPay_status() == PayState.已支付.value()) {
                    if (order.getShip_status() == ShipState.待配送.value()) {
                        order.setStatus(OrderType.待发货.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.待发货.value()));
                    } else if (order.getShip_status() == ShipState.配送中.value()) {
                        order.setStatus(OrderType.待收货.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.待收货.value()));
                    }
                }
            }
        }
        return order;
    }

    @Override
    public PageInfo<Order> getAllByUid(int uid, int pageNum, int pageSize) {
        PageInfo<Order> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> orderDao.getAllByUid(uid));
        return page;
    }

    @Override
    public PageInfo<Order> getAll(int type, Date created_at_start, Date created_at_end, Date time_start, Date time_end, int pageNum, int pageSize) {
        PageInfo<Order> page = null;
        if (OrderType.待支付.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAll(OrderState.进行中.value(), PayState.未支付.value(), ShipState.待配送.value(), PackageState.未打包.value(), created_at_start, created_at_end, time_start, time_end));
        } else if (OrderType.待发货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAll(OrderState.进行中.value(), PayState.已支付.value(), ShipState.待配送.value(), null, created_at_start, created_at_end, time_start, time_end));
        } else if (OrderType.待收货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAll(OrderState.进行中.value(), PayState.已支付.value(), ShipState.配送中.value(), PackageState.已打包.value(), created_at_start, created_at_end, time_start, time_end));
        } else if (OrderType.已完成.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAll(OrderState.已完成.value(), PayState.已支付.value(), ShipState.已配送.value(), PackageState.已打包.value(), created_at_start, created_at_end, time_start, time_end));
        } else if (OrderType.所有.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAll(null, null, null, null, created_at_start, created_at_end, time_start, time_end));
        }
        if (null != page) {
            List<Order> orders = page.getList();
            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setOrderItems(orderItemService.getByOid(orders.get(i).getId()));
            }
        }
        return page;
    }

    @Override
    public OrderCheckDto check(int uid) {
        List<Cart> cartList = cartService.getByUidWithItem(uid);
        double totalAmount = 0.0;
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            cart.setSubTotal(cart.getAmount() * cart.getItemSpec().getShop_price());
            totalAmount += cart.getSubTotal();
        }
        Address address = addressService.getDefaultByUid(uid);
        int bonus_count = bonusService.countByUid(uid);


        OrderCheckDto orderCheckDto = new OrderCheckDto();
        orderCheckDto.setAddress(address);
        orderCheckDto.setBonusCount(bonus_count);
        orderCheckDto.setTotalAmount(totalAmount);
        orderCheckDto.setItems(cartList);

        orderCheckDto.setDate_start(DateTime.now().plusDays(1).toDate());
        orderCheckDto.setDate_end(DateTime.now().plusDays(3).toDate());
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        orderCheckDto.setTime_start(dateFormat.format(DateTime.now().withTimeAtStartOfDay().plusHours(6).toDate()));
        orderCheckDto.setTime_end(dateFormat.format(DateTime.now().withTimeAtStartOfDay().plusHours(9).toDate()));


        return orderCheckDto;
    }

    @Override
    public PageInfo<Order> getByType(int uid, int type, int pageNum, int pageSize) {
        PageInfo<Order> page = null;

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
        } else if (OrderType.所有.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, null, null, null, null));
        }
        if (null != page) {
            List<Order> orders = page.getList();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setOrderItems(orderItemService.getByOid(orders.get(i).getId()));
                if (!OrderType.所有.value().equals(type)) {
                    order.setStatus(type);
                    order.setStatusName(OrderType.getTypeName(type));
                } else {
                    if (order.getOrder_status() == OrderState.已取消.value()) {
                        order.setStatus(OrderType.已取消.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.已取消.value()));
                    } else if (order.getOrder_status() == OrderState.已完成.value()) {
                        order.setStatus(OrderType.已完成.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.已完成.value()));
                    } else if (order.getOrder_status() == OrderState.进行中.value()) {
                        if (order.getPay_status() == PayState.未支付.value()) {
                            order.setStatus(OrderType.待支付.value());
                            order.setStatusName(OrderType.getTypeName(OrderType.待支付.value()));
                        } else if (order.getPay_status() == PayState.已支付.value()) {
                            if (order.getShip_status() == ShipState.待配送.value()) {
                                order.setStatus(OrderType.待发货.value());
                                order.setStatusName(OrderType.getTypeName(OrderType.待发货.value()));
                            } else if (order.getShip_status() == ShipState.配送中.value()) {
                                order.setStatus(OrderType.待收货.value());
                                order.setStatusName(OrderType.getTypeName(OrderType.待收货.value()));
                            }
                        }
                    }
                }
            }
        }
        return page;
    }

    @Override
    public int insert(Order order) {
        order.setNO(StringUtils.generateOrderNo());

        return orderDao.insert(order);
    }

    @Transactional
    @Override
    public int submit(int uid, int aid, int bid, int pid, Date date, Date time_start, Date time_end, String message) {
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


        //计算订单总额
        int order_amount = 0;
        double order_weight = 0;
        double order_money = 0;
        for (int i = 0; i < cartList.size(); i++) {
            order_amount += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getUnit_sell();
            order_weight += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getWeight();
            order_money += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getShop_price();

            //更新库存和销量
            ItemSpec itemSpec = cartList.get(i).getItemSpec();
            int remain = itemSpec.getRemain();
            int sale_num = itemSpec.getSale_num();
            itemSpec.setRemain(remain - cartList.get(i).getAmount() * itemSpec.getUnit_sell());
            itemSpec.setSale_num(sale_num + cartList.get(i).getAmount() * itemSpec.getUnit_sell());
            itemSpecService.updateRemainAndSale(itemSpec);

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
        if (date != null) {
            order.setSend_date(new java.sql.Date(date.getTime()));
            order.setTime_start(new Time(time_start.getTime()));
            order.setTime_end(new Time(time_end.getTime()));
        } else {
            order.setSend_date(new java.sql.Date(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(9).toDate().getTime()));
            order.setTime_start(new Time(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(9).toDate().getTime()));
            order.setTime_end(new Time(DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(10).toDate().getTime()));
        }

        //payment
        if (pid > 0) {
            Payment payment = paymentService.getById(pid);
            if (null != payment) {
                order.setPid(pid);
                order.setPayment(payment.getName());
            }
        }

        //bonus
        if (bid > 0) {
            Bonus bonus = bonusService.getById(bid);
            if (null != bonus) {
                order.setBid(bid);
                order.setBonus(bonus.getMoney());
                bonus.setEnd_at(new Date());
                bonusService.update(bonus);
            }
        }

        int oid = orderDao.insert(order);

        //插入order详情
        for (int i = 0; i < cartList.size(); i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(oid);
            orderItem.setTotal(cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getShop_price());
            BeanUtils.copyProperties(cartList.get(i).getItem(), orderItem, "created_at", "updated_at", "deleted_at");
            BeanUtils.copyProperties(cartList.get(i).getItemSpec(), orderItem, "created_at", "updated_at", "deleted_at");
            BeanUtils.copyProperties(cartList.get(i), orderItem, "created_at", "updated_at", "deleted_at");

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

    @Transactional
    @Override
    public int orderMore(int uid, int oid) {
        Order order = orderDao.getById(oid);
        if (null != order && order.getUid() == uid) {
            List<OrderItem> orderItems = orderItemService.getByOid(order.getId());
            for (int i = 0; i < orderItems.size(); i++) {
                OrderItem orderItem = orderItems.get(i);
                if (itemService.online(orderItem.getGid(), orderItem.getSpec()) > 0) {
                    cartService.saveOrUpdate(uid, orderItem.getGid(), orderItem.getSpec(), orderItem.getAmount());
                }
            }
        }
        return 0;
    }

    @Override
    public int count(Date start, Date end) {
        return orderDao.count(start, end);
    }

    @Override
    public int cancel(int uid, int oid) {
        Order order = orderDao.getById(oid);
        if (order.getUid() == uid) {
            if (order.getPackage_status() == PackageState.已打包.value()) {
                return 0;
            } else if (order.getShip_status() == ShipState.已配送.value() || order.getShip_status() == ShipState.配送中.value()) {
                return 0;
            }
            order.setOrder_status(OrderState.已取消.value());
            return orderDao.cancel(order);
        }
        return 0;
    }

    @Override
    public OrderCountDto orderCount(int uid) {
        OrderCountDto orderCountDto = new OrderCountDto();
        orderCountDto.setOrder_pay(orderDao.countByStatus(uid, OrderState.进行中.value(), PayState.未支付.value(), ShipState.待配送.value(), PackageState.未打包.value()));
        orderCountDto.setOrder_receive(orderDao.countByStatus(uid, OrderState.进行中.value(), PayState.已支付.value(), ShipState.配送中.value(), PackageState.已打包.value()));
        orderCountDto.setOrder_sending(orderDao.countByStatus(uid, OrderState.进行中.value(), PayState.已支付.value(), ShipState.待配送.value(), null));
        orderCountDto.setOrder_finish(orderDao.countByStatus(uid, OrderState.已完成.value(), PayState.已支付.value(), ShipState.已配送.value(), PackageState.已打包.value()));
        return orderCountDto;
    }
}
