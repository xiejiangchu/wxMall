package com.xie.service;

import com.xie.bean.Payment;

/**
 * @Author xie
 * @Date 17/2/24 下午9:57.
 */
public interface PaymentService {

    Payment getById(int id);

    int insert(Payment payment);

    int update(Payment payment);

    int delete(Payment payment);

    int delete(int id);
}
