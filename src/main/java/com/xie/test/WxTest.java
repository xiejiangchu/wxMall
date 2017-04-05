package com.xie.test;

import com.xie.config.WxPayConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xie
 * @summary summary
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description To change this template use File | Settings | File Temp
 * lates.
 * @since 2017-03-28 上午9:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WxTest {

    @Test
    public void testCoreConfig2() {
        System.out.println("==========          单元测试           =============");
        System.out.println(WxPayConfig.getAppID());
        System.out.println("==========          单元测试           =============");
    }
}
