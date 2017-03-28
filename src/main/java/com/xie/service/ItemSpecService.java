package com.xie.service;

import com.xie.bean.ItemSpec;

import java.util.List;

/**
 * @Author xie
 * @Date 17/2/22 下午2:02.
 */
public interface ItemSpecService {
    ItemSpec getById(int id);

    List<ItemSpec> getAllByGid(int gid);

    List<ItemSpec> getOnlineByGid(int gid);

    int insert(ItemSpec itemSpec);

    int update(ItemSpec itemSpec);

    int updateRemainAndSale(ItemSpec itemSpec);

    int delete(ItemSpec itemSpec);

    int delete(int id);

    int softDelete(int id);

    int offline(int id, int is_online);
}
