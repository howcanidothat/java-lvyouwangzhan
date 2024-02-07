package com.yemage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yemage.domain.RouteImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yemage
 */
@Mapper
public interface RouteImgDao extends BaseMapper<RouteImg> {

    @Select("select * from tab_route_img where rid = #{rid}")
    public List<RouteImg> findByRid(Integer rid);

}
