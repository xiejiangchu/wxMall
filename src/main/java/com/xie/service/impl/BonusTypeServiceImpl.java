package com.xie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.bean.BonusType;
import com.xie.dao.BonusTypeDao;
import com.xie.service.BonusTypeService;
import com.xie.service.CategoryService;
import com.xie.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/23 下午12:21.
 */
@Service
public class BonusTypeServiceImpl implements BonusTypeService {

    @Autowired
    private BonusTypeDao bonusTypeDao;
    @Autowired
    BonusTypeService bonusTypeService;

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageInfo<BonusType> getAll(int pageNum, int pageSize) {
        PageInfo<BonusType> page = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> bonusTypeDao.getAll());

        if (page != null && page.getList() != null) {
            List<BonusType> list = page.getList();
            for (int i = 0; i < list.size(); i++) {
                BonusType bonus = list.get(i);
                if (bonus.getGid() > 0 && itemService.getById(bonus.getGid()) != null) {
                    bonus.setGid_name(itemService.getById(bonus.getGid()).getName());
                }
                if (bonus.getCid1() > 0 && categoryService.getById(bonus.getCid1()) != null) {
                    bonus.setCid1_name(categoryService.getById(bonus.getCid1()).getName());
                }
                if (bonus.getCid2() > 0 && categoryService.getById(bonus.getCid2()) != null) {
                    bonus.setCid2_name(categoryService.getById(bonus.getCid2()).getName());
                }
            }
        }
        return page;
    }

    @Override
    public List<BonusType> getAllEnabled() {
        return bonusTypeDao.getAllEnabled();
    }

    @Override
    public List<BonusType> getAllByGid(Integer gid) {
        return bonusTypeDao.getAllByGid(gid);
    }

    @Override
    public List<BonusType> getAllByCid(Integer cid1, Integer cid2) {
        return bonusTypeDao.getAllByCid(cid1, cid2);
    }

    @Override
    public BonusType getById(Integer id) {
        return bonusTypeDao.getById(id);
    }

    @Override
    public int countByGid(Integer gid) {
        return bonusTypeDao.countByGid(gid);
    }

    @Override
    public int insert(BonusType bonusType) {
        return bonusTypeDao.insert(bonusType);
    }

    @Override
    public int update(BonusType bonusType) {
        return bonusTypeDao.update(bonusType);
    }

    @Override
    public int delete(Integer id) {
        return bonusTypeDao.delete(id);
    }

    @Override
    public int delete(BonusType bonusType) {
        return bonusTypeDao.delete(bonusType);
    }


    @Override
    public int saveOrUpdate(BonusType bonus) {
        return bonusTypeDao.saveOrUpdate(bonus);
    }

    @Override
    public int offline(int id, int online) {
        return bonusTypeDao.offline(id,online);
    }
}
