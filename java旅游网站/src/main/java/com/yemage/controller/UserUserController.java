package com.yemage.controller;

import com.yemage.domain.User;
import com.yemage.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserUserController {
    @Resource
    private UserService userService;

    /**
     * 注册账号
     * @param user
     * @return
     */
    @PostMapping("create")
    public Map<String,Object> createAccount(User user){
        return userService.createAccount(user);
    }

    /**
     * 登录账号
     * @param user
     * @return
     */
    @PostMapping ("login")
    public Map<String,Object> loginAccount(User user){
        return userService.loginAccount(user);
    }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
}

