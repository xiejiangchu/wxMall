package com.xie.service.impl;

import com.xie.bean.ItemSpec;
import com.xie.dao.ItemSpecDao;
import com.xie.service.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:03.
 */
@Service
public class ItemSpecServiceImpl implements ItemSpecService {

    @Autowired
    private ItemSpecDao itemSpecDao;

    @Override
    public ItemSpec getById(int id) {
        return itemSpecDao.getById(id);
    }

    @Override
    public List<ItemSpec> getAllByGid(int gid) {
        return itemSpecDao.getAllByGid(gid);
    }

    @Override
    public int insert(ItemSpec itemSpec) {
        return itemSpecDao.insert(itemSpec);
    }

    @Override
    public int update(ItemSpec itemSpec) {
        return itemSpecDao.update(itemSpec);
    }

    @Override
    public int updateRemainAndSale(ItemSpec itemSpec) {
        return itemSpecDao.updateRemainAndSale(itemSpec);
    }

    @Override
    public int delete(ItemSpec itemSpec) {
        return itemSpecDao.delete(itemSpec);
    }

    @Override
    public int delete(int id) {
        return itemSpecDao.delete(id);
    }

    @Override
    public int softDelete(int id) {
        return itemSpecDao.softDelete(id);
    }
}
