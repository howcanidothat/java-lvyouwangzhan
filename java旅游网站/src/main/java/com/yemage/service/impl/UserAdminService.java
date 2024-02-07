package com.yemage.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.yemage.dao.UserDao;
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
public class UserAdminService {

    @Resource
   private UserDao userDao;

    @Resource
    private MailService mailService;

    @Transactional
    public Map<String, Object> creatAccount(UserAdmin user){
        //雪花算法生成确认码
        String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();

        //掩码
        String salt = RandomUtil.randomNumbers(6);

        // 加密密码
        String md5Pwd = SecureUtil.md5(user.getPassword() + salt);
        // 激活失效时间
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        // 初始化账号信息
        user.setSalt(salt);
        user.setPassword(md5Pwd);
        user.setConfirmCode(confirmCode);
        user.setActivationTime(ldt);
        user.setIsValid((byte) 0);

        //新增账号
        int result = userDao.insertUser(user);
        Map<String, Object> resultMap = new HashMap<>();
        if (result > 0){
            // 发送邮件  时间很慢（可以使用异步方式发送：多线程、消息队列）
            String activationUrl = "http://localhost:8080/user/activation?confirmCode="+confirmCode;
            mailService.sendMailForActivationAccount(activationUrl,user.getEmail());
            resultMap.put("code",200);
            resultMap.put("message","注册成功，请前往邮箱进行账号激活");
        }else {
            resultMap.put("code",400);
            resultMap.put("message","注册失败");
        }
        return resultMap;
    }


    public Map<String,Object> loginAccount(UserAdmin user){
        Map<String,Object> resultMap = new HashMap<>();

        //根据邮箱查询用户
        List<UserAdmin> userList = userDao.selectUserByEmail(user.getEmail());
        //查询不到结果，返回；该账户不存在或未激活
        if(userList == null || userList.isEmpty()){
            resultMap.put("code",400);
            resultMap.put("message","该账户不存在或未激活");
            return resultMap;
        }

        // 查询到多个用户，返回：账号异常，请联系管理员
        if(userList.size() > 1){
            resultMap.put("code",400);
            resultMap.put("message","账号异常，请联系管理员");
            return resultMap;
        }

        //查询到一个用户，进行密码比对
        UserAdmin u = userList.get(0);

        //用户输入的密码和掩码进行加密
        String md5Pwd = SecureUtil.md5(user.getPassword() + u.getSalt());

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

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
    @Transactional
    public Map<String, Object> activationAccount(String confirmCode) {
        Map<String, Object> resultMap = new HashMap<>();
        //根据确认码查询用户
        UserAdmin user = userDao.selectUserByConfirmCode(confirmCode);
        System.out.println(user);
        //判断激活时间是否超时
        boolean after = LocalDateTime.now().isAfter(user.getActivationTime());
        if(after){
            resultMap.put("code",400);
            resultMap.put("message","链接已失效，请重新注册");
            return resultMap;
        }
        //根据确认码查询用户，并修改状态值为1（可用）
        int result = userDao.updateUserByConfirmCode(confirmCode);
        if(result > 0){
            resultMap.put("code",200);
            resultMap.put("message","激活成功");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","激活失败");
        }
        return resultMap;
    }
}
