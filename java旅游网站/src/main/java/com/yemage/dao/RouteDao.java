package com.yemage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yemage.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yemage
 */
@Mapper
public interface RouteDao extends BaseMapper<Route> {

    /**
     * 分页查询
     * @param conditioin
     * @return
     */
    public List<Route> find(Route conditioin);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    public Route findById(Integer id);

}
