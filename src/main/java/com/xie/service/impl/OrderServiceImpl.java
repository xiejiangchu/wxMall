package com.xie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thoughtworks.xstream.XStream;
import com.xie.auth.MyUserDetails;
import com.xie.bean.*;
import com.xie.config.MyWxPayConfig;
import com.xie.dao.OrderDao;
import com.xie.enums.*;
import com.xie.pay.common.HttpRequest;
import com.xie.pay.common.Signature;
import com.xie.pay.model.OrderInfo;
import com.xie.pay.model.OrderReturnInfo;
import com.xie.pay.model.SignInfo;
import com.xie.response.OrderCheckDto;
import com.xie.response.OrderCountDto;
import com.xie.service.*;
import com.xie.utils.MallConstants;
import com.xie.utils.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author xie
 * @Date 17/1/23 上午10:36.
 */
@Service
public class OrderServiceImpl implements OrderService {

    protected final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

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

    @Autowired
    private PointService pointService;

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
                } else if (order.getPay_status() == PayState.货到付款.value()) {
                    if (order.getShip_status() == ShipState.待配送.value()) {
                        order.setStatus(OrderType.待发货.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.待发货.value()));
                    } else if (order.getShip_status() == ShipState.配送中.value()) {
                        order.setStatus(OrderType.待收货.value());
                        order.setStatusName(OrderType.getTypeName(OrderType.待收货.value()));
                    }
                }
            }
            order.setOrderItems(orderItemService.getByOid(id));
            order.setUser(userService.getById(order.getUid()));
        }
        return order;
    }

    @Override
    public Order getByNo(String no) {
        return orderDao.getByNo(no);
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
                orders.get(i).setUser(userService.getById(orders.get(i).getUid()));
            }
        }
        return page;
    }

    @Override
    public OrderCheckDto check(int uid) {
        List<Cart> cartList = cartService.getByUidWithItem(uid);
        List<Cart> carts = new ArrayList<>();
        double totalAmount = 0.0;
        int promote_price = 0;
        int changed = MallConstants.NO;
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            if (cart.getItem().getIs_online() == MallConstants.YES && cart.getItemSpec().getIs_online() == MallConstants.YES) {
                cart.setSubTotal(cart.getAmount() * cart.getItemSpec().getShop_price());
                totalAmount += cart.getSubTotal();
                promote_price += cart.getAmount() * cart.getItemSpec().getPromote_price();
                carts.add(cart);
            } else {
                changed = MallConstants.ERROR_ITEM_CHANGED;
            }
        }
        Address address = addressService.getFirstAddress(uid);
        int bonus_count = bonusService.countEnabledByCart(uid, carts);

        if (promote_price > pointService.getByUid(uid).getPoints()) {
            changed = MallConstants.ERROR_POINT_NOT_ENOUGH;
        }
        if (totalAmount < MallConstants.ORDER_MIN_MONEY) {
            changed = MallConstants.ERROR_LT_ORDER_MIN_MONEY;
        }


        OrderCheckDto orderCheckDto = new OrderCheckDto();
        orderCheckDto.setPromoto_price(promote_price);
        orderCheckDto.setAddress(address);
        orderCheckDto.setBonusCount(bonus_count);
        orderCheckDto.setTotalAmount(totalAmount);
        orderCheckDto.setItems(carts);
        orderCheckDto.setPayments(paymentService.getEnabled());
        orderCheckDto.setPoint_rate(MallConstants.POINT_RATE);
        Point point = pointService.getByUid(uid);
        if (null == point) {
            Point point_insert = new Point();
            point_insert.setUid(uid);
            point_insert.setMoney(0);
            point_insert.setPoints(0);
            pointService.insert(point_insert);
            orderCheckDto.setPoint(0);
        } else {
            orderCheckDto.setPoint(point.getPoints());
        }
        orderCheckDto.setChanged(changed);

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
                    () -> orderDao.getByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.未支付.value()), Arrays.asList(ShipState.待配送.value()), Arrays.asList(PackageState.未打包.value())));
        } else if (OrderType.待发货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.已支付.value(), PayState.货到付款.value()), Arrays.asList(ShipState.待配送.value()), null));
        } else if (OrderType.待收货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.已支付.value(), PayState.货到付款.value()), Arrays.asList(ShipState.配送中.value()), Arrays.asList(PackageState.已打包.value())));
        } else if (OrderType.已完成.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, Arrays.asList(OrderState.已完成.value(), OrderState.已取消.value()), null, null, null));
        } else if (OrderType.所有.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getByStatus(uid, null, null, null, null));
        }
        if (null != page) {
            List<Order> orders = page.getList();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setOrderItems(orderItemService.getByOid(orders.get(i).getId()));
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
                    } else if (order.getPay_status() == PayState.货到付款.value()) {
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
        return page;
    }

    @Override
    public PageInfo<Order> getAllByType(int type, int pageNum, int pageSize) {
        PageInfo<Order> page = null;

        if (OrderType.待支付.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAllByStatus(OrderState.进行中.value(), PayState.未支付.value(), ShipState.待配送.value(), PackageState.未打包.value()));
        } else if (OrderType.待发货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAllByStatus(OrderState.进行中.value(), PayState.已支付.value(), ShipState.待配送.value(), null));
        } else if (OrderType.待收货.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAllByStatus(OrderState.进行中.value(), PayState.已支付.value(), ShipState.配送中.value(), PackageState.已打包.value()));
        } else if (OrderType.已完成.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAllByStatus(OrderState.已完成.value(), PayState.已支付.value(), ShipState.已配送.value(), PackageState.已打包.value()));
        } else if (OrderType.所有.value().equals(type)) {
            page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(
                    () -> orderDao.getAllByStatus(null, null, null, null));
        }
        if (null != page) {
            List<Order> orders = page.getList();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                order.setOrderItems(orderItemService.getByOid(orders.get(i).getId()));
                order.setUser(userService.getById(order.getUid()));
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
                        } else if (order.getPay_status() == PayState.货到付款.value()) {
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
    public int submit(int uid, int point, int aid, int bid, int pid, Date date, Date time_start, Date time_end, String message) {
        List<Cart> cartList = cartService.getByUidWithItem(uid);

        if (null == cartList || cartList.size() == 0) {
            return -1;
        }
        List<OrderItem> orderItems = new ArrayList<>();
        Address address = addressService.getById(aid);
        Order order = new Order();

        order.setNO(StringUtils.generateOrderNo());
        order.setUid(uid);

        //payment
        if (pid > 0) {
            Payment payment = paymentService.getById(pid);
            if (null != payment) {
                order.setPid(pid);
                order.setPayment(payment.getName());
                if (payment.getName().trim().equals(MallConstants.PAYMENT_CASH)) {

                    order.setConfirmed(ConfirmState.已确认.value());
                    order.setOrder_status(OrderState.进行中.value());
                    order.setPay_status(PayState.货到付款.value());
                    order.setShip_status(ShipState.待配送.value());
                    order.setPackage_status(PackageState.未打包.value());
                } else {

                    order.setConfirmed(ConfirmState.已确认.value());
                    order.setOrder_status(OrderState.进行中.value());
                    order.setPay_status(PayState.未支付.value());
                    order.setShip_status(ShipState.待配送.value());
                    order.setPackage_status(PackageState.未打包.value());
                }
            }
        }


        //计算订单总额
        int order_amount = 0;
        double order_weight = 0;
        double order_money = 0;
        double order_total = 0;
        int promote_price = 0;
        for (int i = 0; i < cartList.size(); i++) {
            order_amount += cartList.get(i).getAmount();
            order_weight += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getWeight();
            order_total += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getShop_price();
            promote_price += cartList.get(i).getAmount() * cartList.get(i).getItemSpec().getPromote_price();
            //更新库存和销量
            ItemSpec itemSpec = cartList.get(i).getItemSpec();
            int remain = itemSpec.getRemain();
            int sale_num = itemSpec.getSale_num();
            itemSpec.setRemain(remain - cartList.get(i).getAmount());
            itemSpec.setSale_num(sale_num + cartList.get(i).getAmount() * itemSpec.getUnit_sell());
            itemSpecService.updateRemainAndSale(itemSpec);

        }


        int totalPoint = point + promote_price;
        if (totalPoint > pointService.getByUid(uid).getPoints()) {
            return MallConstants.ERROR_POINT_NOT_ENOUGH;
        }
        if (order_total < MallConstants.ORDER_MIN_MONEY) {
            return MallConstants.ERROR_LT_ORDER_MIN_MONEY;
        }
        order.setOrder_total(order_total);
        order_money = order_total;

        //point
        order.setPoint_used(point + promote_price);
        order_money = order_money - (double) point / MallConstants.POINT_RATE;
        int point_add = (int) (order_total / 10);
        order.setPoint(point_add);
        pointService.add(uid, 0, point_add - point - promote_price);

        //地址操作
        order.setAddress_id(address.getId());
        order.setProvince(address.getProvince());
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


        //bonus
        if (bid > 0) {
            Bonus bonus = bonusService.getEnabledById(bid);
            if (null != bonus) {
                order.setBid(bid);
                order.setBonus(bonus.getMoney());
                bonus.setEnd_at(new Date());
                bonus.setIs_enable(0);
                bonusService.update(bonus);
                order_money = order_money - bonus.getMoney();
            }
        }

        order.setOrder_amount(order_amount);
        order.setOrder_weight(order_weight);
        order.setOrder_money(order_money);

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
            return 1;
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
    public int packageOrder(int oid, int package_status) {
        Order order = orderDao.getById(oid);
        if (order.getOrder_status() == OrderState.已取消.value() || order.getOrder_status() == OrderState.已完成.value() || order.getOrder_status() == OrderState.已删除.value() || order.getOrder_status() == OrderState.系统回收.value()) {
            return MallConstants.NO;
        } else if (order.getPackage_status() == PackageState.已打包.value()) {
            return MallConstants.NO;
        } else if (order.getShip_status() == ShipState.已配送.value() || order.getShip_status() == ShipState.配送中.value()) {
            return MallConstants.NO;
        }
        if (package_status == PackageState.已打包.value() || package_status == PackageState.未打包.value()) {
            order.setPackage_status(package_status);
            return orderDao.packageOrder(order);
        }

        return MallConstants.NO;
    }

    @Override
    public int sendOrder(int oid, int ship_status) {
        Order order = orderDao.getById(oid);
        if (order.getOrder_status() == OrderState.已取消.value() || order.getOrder_status() == OrderState.已完成.value() || order.getOrder_status() == OrderState.已删除.value() || order.getOrder_status() == OrderState.系统回收.value()) {
            return MallConstants.NO;
        } else if (order.getPackage_status() == PackageState.已打包.value()) {
            return MallConstants.NO;
        } else if (order.getShip_status() == ShipState.已配送.value() || order.getShip_status() == ShipState.配送中.value()) {
            return MallConstants.NO;
        }
        if (ship_status == ShipState.已配送.value() || ship_status == ShipState.待配送.value() || ship_status == ShipState.配送中.value()) {
            order.setPackage_status(PackageState.已打包.value());
            order.setShip_status(ship_status);
            return orderDao.sendOrder(order);
        }
        return MallConstants.NO;
    }

    @Override
    public int cancelOrder(int oid) {
        Order order = orderDao.getById(oid);
        if (order.getOrder_status() == OrderState.已取消.value() || order.getOrder_status() == OrderState.已完成.value() || order.getOrder_status() == OrderState.已删除.value() || order.getOrder_status() == OrderState.系统回收.value()) {
            return MallConstants.NO;
        } else if (order.getPackage_status() == PackageState.已打包.value()) {
            return MallConstants.NO;
        } else if (order.getShip_status() == ShipState.已配送.value() || order.getShip_status() == ShipState.配送中.value()) {
            return MallConstants.NO;
        }
        order.setOrder_status(OrderState.已取消.value());
        return orderDao.cancel(order);
    }

    @Override
    public int updatePrepayId(int oid,String prepay_id) {
        return orderDao.updatePrepayId(oid,prepay_id);
    }

    @Override
    public OrderCountDto orderCount(int uid) {
        OrderCountDto orderCountDto = new OrderCountDto();
        orderCountDto.setOrder_pay(orderDao.countByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.未支付.value()), Arrays.asList(ShipState.待配送.value()), Arrays.asList(PackageState.未打包.value())));
        orderCountDto.setOrder_receive(orderDao.countByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.已支付.value(), PayState.货到付款.value()), Arrays.asList(ShipState.配送中.value()), Arrays.asList(PackageState.已打包.value())));
        orderCountDto.setOrder_sending(orderDao.countByStatus(uid, Arrays.asList(OrderState.进行中.value()), Arrays.asList(PayState.已支付.value(), PayState.货到付款.value()), Arrays.asList(ShipState.待配送.value()), null));
        orderCountDto.setOrder_finish(orderDao.countByStatus(uid, Arrays.asList(OrderState.已完成.value(), OrderState.已取消.value()), null, null, null));
        Point point = pointService.getByUid(uid);
        if (null == point) {
            Point point_insert = new Point();
            point_insert.setUid(uid);
            point_insert.setMoney(0);
            point_insert.setPoints(0);
            pointService.insert(point_insert);
            orderCountDto.setPoint(0);
            orderCountDto.setMoney(0);
        } else {
            orderCountDto.setPoint(point.getPoints());
            orderCountDto.setMoney(point.getMoney());
        }

        MyUserDetails myUserDetails = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
        }
        if (myUserDetails != null && myUserDetails.isAdmin()) {
            orderCountDto.setAdmins(MallConstants.YES);
        } else {
            orderCountDto.setAdmins(MallConstants.NO);
        }

        return orderCountDto;
    }

    @Override
    public OrderReturnInfo pay(int uid, int oid, String ip) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, IllegalAccessException, UnrecoverableKeyException {
        User user = userService.getById(uid);
        Order order = orderDao.getById(oid);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppid(MyWxPayConfig.getAppId());
        orderInfo.setMch_id(MyWxPayConfig.getMch_id());
        orderInfo.setNonce_str(StringUtils.randomString(32));
        orderInfo.setBody("腾讯充值中心-QQ会员充值");
        orderInfo.setOut_trade_no(order.getNO());
        orderInfo.setTotal_fee((int) (order.getOrder_money() * 100));
        orderInfo.setSpbill_create_ip(ip);
        orderInfo.setNotify_url(MyWxPayConfig.getNotify_url());
        orderInfo.setTrade_type("JSAPI");
        orderInfo.setOpenid(user.getOpenId());
        orderInfo.setSign_type("MD5");

        //生成签名
        String sign = Signature.getSign(orderInfo);
        orderInfo.setSign(sign);

        String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", orderInfo);
        logger.info("请求支付结果{}",result);
        OrderReturnInfo returnInfo = null;
        if (null != result) {
            XStream xStream = new XStream();
            xStream.alias("xml", OrderReturnInfo.class);
            returnInfo = (OrderReturnInfo) xStream.fromXML(result);
            returnInfo.setTimeStamp(DateTime.now().toDate());
        }
        return returnInfo;
    }

    @Override
    public JSONObject sign(String repay_id) {
        try {
            long time = System.currentTimeMillis() / 1000;
            SignInfo signInfo = new SignInfo();
            signInfo.setAppId(MyWxPayConfig.getAppId());
            signInfo.setTimeStamp(String.valueOf(time));
            signInfo.setNonceStr(StringUtils.randomNumber(32));
            signInfo.setRepay_id("prepay_id=" + repay_id);
            signInfo.setSignType("MD5");
            //生成签名
            String sign = Signature.getSign(signInfo);

            JSONObject json = new JSONObject();
            json.put("timeStamp", signInfo.getTimeStamp());
            json.put("nonceStr", signInfo.getNonceStr());
            json.put("package", signInfo.getRepay_id());
            json.put("signType", signInfo.getSignType());
            json.put("paySign", sign);
            return json;
        } catch (Exception e) {
            logger.info("签名失败");
        }
        return null;
    }
}
