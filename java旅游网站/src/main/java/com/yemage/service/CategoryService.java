package com.yemage.service;

import com.yemage.domain.Category;

import java.util.List;

/**
 * @author yemage
 */
public interface CategoryService {

    public List<Category> find();

    /**
     * 添加
     * @param category
     * @return
     */
    public int add(Category category);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Category findById(Integer id);

    /**
     * 修改
     * @param category
     * @return
     */
    public int update(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

    public Category ifdelete(Integer id);
    public Category ifadd(String cid);


}
