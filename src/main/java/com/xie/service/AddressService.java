package com.xie.service;

import com.github.pagehelper.PageInfo;
import com.xie.bean.Address;

import java.util.List;

/**
 * @Author xie
 * @Date 17/1/22 下午3:17.
 */
public interface AddressService {
    List<Address> getByUid(int uid);

    PageInfo<Address> getByUid(int uid, int pageNum, int pageSize);

    Address getDefaultByUid(int uid);

    Address getFirstAddress(int uid);

    Address getById(int id);

    List<Address> getByMobile(String mobile);

    int countByUid(int uid);

    int insert(Address address);

    int update(Address address);

    int delete(Address address);

    int delete(int id);

    int softDelete(int id);
}
