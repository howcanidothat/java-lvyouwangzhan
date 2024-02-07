package com.yemage.service;

import com.github.pagehelper.PageInfo;
import com.yemage.domain.User;
import com.yemage.domain.UserAdmin;

import java.util.List;
import java.util.Map;

/**
 * @author yemage
 */
public interface UserService {

    /**
     * 分页查询
     * @param conditioin 查询条件
     * @return
     */
    public PageInfo<User> findPage(User conditioin, int pageNum, int pageSize);

    /**
     * 查询
     * @param conditioin 查询条件
     * @return
     */
    public List<User> find(User conditioin);

    /**
     * 添加
     * @param user
     * @return
     */
    public int add(User user);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 修改
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

   // public Map<String,Object> loginAccount(UserAdmin user);
   // public Map<String, Object> activationAccount(String confirmCode);
   //public Map<String,Object> creatAccount(UserAdmin user);
}
