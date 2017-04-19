package com.xie.aspect;

import com.xie.bean.OrderLog;
import com.xie.enums.ActionType;
import com.xie.service.OrderLogService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xie on 17/1/6.
 */
@Aspect
@Component
public class OrderLogAspect {

    @Autowired
    private OrderLogService orderLogService;


    @Pointcut("execution(* com.xie.service.impl.OrderServiceImpl.pay(..))")
    public void pay() {
    }

    @Pointcut("execution(* com.xie.service.impl.OrderServiceImpl.cancel(..))")
    public void cancel() {
    }

    @Pointcut("execution(* com.xie.service.impl.OrderServiceImpl.orderMore(..))")
    public void orderMore() {
    }

    @Pointcut("execution(* com.xie.service.impl.OrderServiceImpl.submit(..))")
    public void submit() {
    }

    @AfterReturning(pointcut = "pay() && args(uid,oid,ip,..)")
    public void payPointAfter(int uid, int oid, String ip) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOid(oid);
        orderLog.setType(ActionType.订单操作.value());
        orderLog.setAction("请求支付订单");
        orderLog.setDetails("请求支付订单");
        orderLog.setOperator(uid + "");
        orderLogService.insert(orderLog);
    }

    @AfterReturning(pointcut = "cancel() && args(uid,oid,..)")
    public void cancelPointAfter(int uid, int oid) {
        //订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOid(oid);
        orderLog.setType(ActionType.订单操作.value());
        orderLog.setAction("取消订单");
        orderLog.setDetails("取消订单");
        orderLog.setOperator(uid + "");
        orderLogService.insert(orderLog);
    }

    @AfterReturning(pointcut = "orderMore() && args(uid,oid,..)")
    public void orderMorePointAfter(int uid, int oid) {
        //订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOid(oid);
        orderLog.setType(ActionType.订单操作.value());
        orderLog.setAction("加入购物车");
        orderLog.setDetails("再次加入购物车");
        orderLog.setOperator(uid + "");
        orderLogService.insert(orderLog);
    }

    @AfterReturning(pointcut = "submit() && args(uid,..)", returning = "oid")
    public void submitPointAfter(int uid, int oid) {
        //订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOid(oid);
        orderLog.setType(ActionType.订单操作.value());
        orderLog.setAction("提交了订单");
        orderLog.setDetails("提交了订单");
        orderLog.setOperator(uid + "");
        orderLogService.insert(orderLog);
    }
}