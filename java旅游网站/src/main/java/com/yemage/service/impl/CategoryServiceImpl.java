package com.yemage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yemage.dao.CategoryDao;
import com.yemage.domain.Category;
import com.yemage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yemage
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> find() {
        return categoryDao.selectList(Wrappers.query());
    }

    @Override
    public int add(Category category) {
        return categoryDao.insert(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.selectById(id);
    }

    @Override
    public int update(Category category) {
        return categoryDao.updateById(category);
    }

    @Override
    public int delete(Integer id) {
        return categoryDao.deleteById(id);
    }

    @Override
    public Category ifdelete(Integer id) {
        return categoryDao.ifdelete(id);
    }

    @Override
    public Category ifadd(String cname) {
        return  categoryDao.ifadd(cname);
    }

}
