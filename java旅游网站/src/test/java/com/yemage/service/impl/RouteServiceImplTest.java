package com.yemage.service.impl;

import com.github.pagehelper.PageInfo;
import com.yemage.domain.Route;
import com.yemage.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yemage
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {

    @Autowired
    private RouteService routeService;

    @Test
    public void findPage() {
        Route condition = new Route();
        condition.setRname("北京");
        PageInfo<Route> page = routeService.findPage(condition, 1, 10);

        page.getList().forEach((r) -> {
            System.out.println(r.getRid() + "\t" + r.getRname() + "\t" + r.getCategory().getCname() + "\t" + r.getSeller().getSname() + "\t" + r.getRouteImgList().size());
        });
    }

    @Test
    public void findById() {
        Route r = routeService.findById(34);
        System.out.println(r.getRid() + "\t" + r.getRname() + "\t" + r.getCategory().getCname() + "\t" + r.getSeller().getSname() + "\t" + r.getRouteImgList().size());
    }
}