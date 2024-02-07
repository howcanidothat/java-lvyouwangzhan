package com.yemage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yemage.domain.Category;
import com.yemage.domain.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yemage
 */
@Mapper
public interface SellerDao extends BaseMapper<Seller> {

    @Select("SELECT * FROM tab_seller WHERE sid=#{id}")
    public Seller findById(Integer id);

    @Select("DELETE FROM tab_seller WHERE Sid NOT IN (SELECT Sid FROM tab_route);")
    public Seller ifdelete(Integer cid);

    @Select("DELETE FROM tab_seller WHERE Sid NOT IN (SELECT Sid FROM tab_route);")
    public Seller ifadd(Integer cid);

}
