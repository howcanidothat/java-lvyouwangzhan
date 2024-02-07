package com.yemage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yemage.dao.RouteImgDao;
import com.yemage.domain.RouteImg;
import com.yemage.service.RouteImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yemage
 */
@Service
public class RouteImgServiceImpl implements RouteImgService {

    @Autowired
    private RouteImgDao routeImgDao;

    @Override
    @Transactional
    public void saveImg(Integer rid, List<RouteImg> routeImgs) {
        routeImgDao.delete(Wrappers.<RouteImg>query().eq("rid",rid));
        for(RouteImg r : routeImgs){
            routeImgDao.insert(r);
        }
    }
}
