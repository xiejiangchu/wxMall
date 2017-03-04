package com.xie.service.impl;

import com.xie.response.DashBoardDto;
import com.xie.service.ItemService;
import com.xie.service.OrderService;
import com.xie.service.StatisticsService;
import com.xie.service.UserService;
import com.xie.utils.MallConstants;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xie
 * @Date 17/3/4 上午9:54.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Override
    public DashBoardDto dashboard() {
        DashBoardDto dashBoardDto = new DashBoardDto();
        dashBoardDto.setItem_count(itemService.count(false));
        dashBoardDto.setOrder_count(orderService.count(null, null));
        dashBoardDto.setOrder_count_1(orderService.count(DateTime.now().minusDays(1).toDate(), DateTime.now().toDate()));
        dashBoardDto.setUser_count(userService.count());
        dashBoardDto.setLast(itemService.last(1, MallConstants.PAGESIZE).getList());
        dashBoardDto.setLastUpdated(itemService.lastUpdated(1, MallConstants.PAGESIZE).getList());
        return dashBoardDto;
    }
}
