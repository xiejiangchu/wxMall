package com.xie.service.impl;

import com.xie.bean.Image;
import com.xie.bean.ItemImage;
import com.xie.dao.ItemImageDao;
import com.xie.service.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/24 下午3:54.
 */
@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    private ItemImageDao itemImageDao;

    @Override
    public ItemImage getById(int id) {
        return itemImageDao.getById(id);
    }

    @Override
    public List<Image> getByIid(int iid) {
        return itemImageDao.getByIid(iid);
    }

    @Override
    public int insert(ItemImage itemImage) {
        return itemImageDao.insert(itemImage);
    }

    @Override
    public int check(int iid, int imgid) {
        return itemImageDao.check(iid,imgid);
    }

    @Override
    public int insert(int iid, int imgid, int type) {
        Assert.notNull(iid);
        Assert.notNull(imgid);
        ItemImage itemImage = new ItemImage();
        itemImage.setIid(iid);
        itemImage.setImgid(imgid);
        itemImage.setType(type);
        return itemImageDao.insert(itemImage);
    }

    @Override
    public int update(ItemImage itemImage) {
        return itemImageDao.update(itemImage);
    }

    @Override
    public int delete(ItemImage itemImage) {
        return itemImageDao.delete(itemImage);
    }

    @Override
    public int delete(int id) {
        return itemImageDao.delete(id);
    }

    @Override
    public int delete(List<Integer> ids) {
        return itemImageDao.delete(ids);
    }

    @Override
    public int deleteByIid(int iid) {
        return itemImageDao.deleteByIid(iid);
    }
}
