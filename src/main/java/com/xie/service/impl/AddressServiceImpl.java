package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.Address;
import com.xie.dao.AddressDao;
import com.xie.service.AddressService;
import com.xie.utils.MallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author xie
 * @Date 17/1/22 下午3:20.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> getByUid(int uid) {
        return addressDao.getByUid(uid);
    }

    @Override
    public PageInfo<Address> getByUid(int uid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Address> page = new PageInfo<Address>(addressDao.getByUid(uid));
        return page;
    }

    @Override
    public Address getDefaultByUid(int uid) {
        return addressDao.getDefaultByUid(uid);
    }

    @Override
    public Address getFirstAddress(int uid) {
        return addressDao.getFirstAddress(uid);
    }

    @Override
    public Address getById(int id) {
        return addressDao.getById(id);
    }

    @Override
    public List<Address> getByMobile(String mobile) {
        return addressDao.getByMobile(mobile);
    }

    @Override
    public int countByUid(int uid) {
        return addressDao.countByUid(uid);
    }

    @Override
    public int insert(Address address) {
        Assert.notNull(address);
        if (address.getIs_def() == MallConstants.YES) {
            addressDao.removeDefaultByUid(address.getUid());
        }
        return addressDao.insert(address);
    }

    @Override
    public int update(Address address) {
        Assert.notNull(address);
        Assert.isTrue(address.getId() > 0);
        if (address.getIs_def() == MallConstants.NO && addressDao.getById(address.getId()).getIs_def() == MallConstants.YES) {
            return 0;
        }
        return addressDao.update(address);
    }

    @Override
    public int delete(Address address) {
        Assert.notNull(address);
        Assert.isTrue(address.getId() > 0);
        return addressDao.delete(address.getId());
    }

    @Override
    public int delete(int id) {
        Assert.isTrue(id > 0);
        return addressDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        Assert.isTrue(id > 0);
        return addressDao.softDelete(id);
    }
}
