package com.xie.service.impl;

import com.xie.bean.Payment;
import com.xie.dao.PaymentDao;
import com.xie.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午9:58.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public List<Payment> getAll() {
        return paymentDao.getAll();
    }

    @Override
    public List<Payment> getEnabled() {
        return paymentDao.getEnabled();
    }

    @Override
    public Payment getById(int id) {
        return paymentDao.getById(id);
    }

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public int update(Payment payment) {
        return paymentDao.update(payment);
    }

    @Override
    public int delete(Payment payment) {
        return paymentDao.delete(payment);
    }

    @Override
    public int delete(int id) {
        return paymentDao.delete(id);
    }
}
