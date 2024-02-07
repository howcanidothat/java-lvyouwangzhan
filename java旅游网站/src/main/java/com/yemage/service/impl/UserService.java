package com.yemage.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.yemage.dao.UserDao;
import com.yemage.domain.User;
import com.yemage.domain.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private MailServiceUser mailServiceUser;

    @Transactional
    public Map<String, Object> createAccount(User user){
        // 加密密码
        String md5Pwd = SecureUtil.md5(user.getPassword());

        // 初始化账号信息
        user.setPassword(md5Pwd);
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());



        //新增账号
        int result = userDao.insertUser1(user);
        Map<String, Object> resultMap = new HashMap<>();
        if (result > 0){
            // 发送邮件  时间很慢（可以使用异步方式发送：多线程、消息队列）
            String activationUrl = "http://localhost:8080/user";
            mailServiceUser.sendMailForActivationuserAccount(activationUrl,user.getEmail());
            resultMap.put("code",200);
            resultMap.put("message","注册成功，请前往邮箱进行账号验证");
        }else {
            resultMap.put("code",400);
            resultMap.put("message","注册失败");
        }
        return resultMap;
    }


    public Map<String,Object> loginAccount(User user){
        Map<String,Object> resultMap = new HashMap<>();

        //根据邮箱查询用户
        List<User> userList = userDao.selectUserAdminByEmail(user.getEmail());

        // 查询到多个用户，返回：账号异常，请联系管理员
        if(userList.size() > 1){
            resultMap.put("code",400);
            resultMap.put("message","账号异常，请联系管理员");
            return resultMap;
        }

        //查询到一个用户，进行密码比对
        User u = userList.get(0);

        //用户输入的密码和掩码进行加密
        String md5Pwd = SecureUtil.md5(user.getPassword());

        //密码不一致，返回：用户名或密码错误
        if(!u.getPassword().equals(md5Pwd)){
            resultMap.put("code",400);
            resultMap.put("message","用户名或密码错误");
            return resultMap;
        }
        resultMap.put("code",200);
        resultMap.put("message","登陆成功");
        return resultMap;
    }
}
