package com.yemage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yemage.dao.SellerDao;
import com.yemage.domain.Category;
import com.yemage.domain.Seller;
import com.yemage.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yemage
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;

    @Override
    public PageInfo<Seller> findPage(Seller conditioin, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            sellerDao.selectList(new QueryWrapper<Seller>());
        });
    }

    @Override
    public List<Seller> find(Seller condition) {
        return sellerDao.selectList(Wrappers.query());
    }

    @Override
    public int add(Seller seller) {
        return sellerDao.insert(seller);
    }

    @Override
    public Seller findById(Integer id) {
        return sellerDao.selectById(id);
    }

    @Override
    public int update(Seller seller) {
        return sellerDao.updateById(seller);
    }

    @Override
    public int delete(Integer id) {
        return sellerDao.deleteById(id);
    }

    @Override
    public Seller ifdelete(Integer id) {
        return sellerDao.ifdelete(id);
    }

    @Override
    public Seller ifadd(Integer sid) {
        return  sellerDao.ifadd(sid);
    }

}
