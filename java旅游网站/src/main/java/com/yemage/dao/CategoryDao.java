package com.yemage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yemage.domain.Category;
import com.yemage.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @author yemage
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

        @Select("SELECT cname,cid FROM tab_category WHERE cid=#{id}")
        public Category findById(Integer id);

        @Select("DELETE FROM tab_category WHERE cid NOT IN (SELECT cid FROM tab_route);")
        public Category ifdelete(Integer cid);

        @Select("SELECT COUNT(*) FROM tab_categoryb WHERE cname = #{cname}")
        public Category ifadd(String cname);
}
