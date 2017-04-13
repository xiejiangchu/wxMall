package com.xie.service.impl;

import com.xie.bean.ItemSpec;
import com.xie.dao.ItemSpecDao;
import com.xie.service.ItemSpecService;
import com.xie.utils.MallConstants;
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
    public List<ItemSpec> getOnlineByGid(int gid) {
        return itemSpecDao.getOnlineByGid(gid);
    }

    @Override
    public int insert(ItemSpec itemSpec) {
        if (itemSpec.getUnit_sell() <= 0) {
            itemSpec.setUnit_sell(1);
        }
        return itemSpecDao.insert(itemSpec);
    }

    @Override
    public int update(ItemSpec itemSpec) {

        if (itemSpec.getUnit_sell() <= 0) {
            itemSpec.setUnit_sell(1);
        }
        return itemSpecDao.update(itemSpec);
    }

    @Override
    public int updateRemainAndSale(ItemSpec itemSpec) {
        if (itemSpec.getRemain() <= 0) {
            itemSpec.setIs_remain(MallConstants.NO);
        }
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

    @Override
    public int offline(int id, int is_online) {
        return itemSpecDao.offline(id, is_online);
    }
}
