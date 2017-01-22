package com.xie.dao;

import com.xie.bean.Address;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class AddressDao extends BaseDao {

    public List<Address> getByUid(int uid) {
        return this.sqlSession.selectList("AddressDao.getByUid", uid);
    }

    public Address getDefaultByUid(int uid) {
        return this.sqlSession.selectOne("AddressDao.getDefaultByUid", uid);
    }

    public Address getById(int id) {
        return this.sqlSession.selectOne("AddressDao.getById", id);
    }

    public List<Address> getByMobile(String mobile) {
        return this.sqlSession.selectOne("AddressDao.getByMobile", mobile);
    }

    public int countByUid(int uid) {
        return this.sqlSession.selectOne("AddressDao.countByUid", uid);
    }

    public int insert(Address address) {
        return this.sqlSession.insert("AddressDao.insert", address);
    }

    public int update(Address address) {
        return this.sqlSession.update("AddressDao.update", address);
    }

    public int delete(int id) {
        return this.sqlSession.delete("AddressDao.delete", id);
    }

    public int softDelete(int id) {
        return this.sqlSession.update("AddressDao.softDelete", id);
    }

}