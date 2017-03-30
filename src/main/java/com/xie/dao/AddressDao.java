package com.xie.dao;

import com.xie.bean.Address;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xie on 16/11/24.
 */

@Component
public class AddressDao extends BaseDao {

    public List<Address> getByUid(Integer uid) {
        return this.sqlSession.selectList("AddressDao.getByUid", uid);
    }

    public Address getDefaultByUid(Integer uid) {
        return this.sqlSession.selectOne("AddressDao.getDefaultByUid", uid);
    }

    public Address removeDefaultByUid(Integer uid) {
        return this.sqlSession.selectOne("AddressDao.removeDefaultByUid", uid);
    }

    public Address getById(Integer id) {
        return this.sqlSession.selectOne("AddressDao.getById", id);
    }

    public List<Address> getByMobile(String mobile) {
        return this.sqlSession.selectOne("AddressDao.getByMobile", mobile);
    }

    public Address getFirstAddress(int uid){
        return this.sqlSession.selectOne("AddressDao.getFirstAddress", uid);
    }

    public int countByUid(Integer uid) {
        return this.sqlSession.selectOne("AddressDao.countByUid", uid);
    }

    public int insert(Address address) {
        return this.sqlSession.insert("AddressDao.insert", address);
    }

    public int update(Address address) {
        return this.sqlSession.update("AddressDao.update", address);
    }

    public int delete(Integer id) {
        return this.sqlSession.delete("AddressDao.delete", id);
    }

    public int softDelete(Integer id) {
        return this.sqlSession.update("AddressDao.softDelete", id);
    }
}