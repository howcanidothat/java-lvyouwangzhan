package com.yemage.controller;


import com.yemage.domain.UserAdmin;
import com.yemage.service.impl.UserAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class UserAdminController {
    @Resource
    private UserAdminService userAdminService;

    /**
     * 注册账号
     * @param user
     * @return
     */
    @PostMapping("create")
    public Map<String,Object> createAccount(UserAdmin user){
        return userAdminService.creatAccount(user);
    }

    /**
     * 登录账号
     * @param user
     * @return
     */
    @PostMapping ("login")
    public Map<String,Object> loginAccount(UserAdmin user){
        return userAdminService.loginAccount(user);
    }

    /**
     * 激活账号
     * @param confirmCode
     * @return
     */
    @GetMapping("activation")
    public Map<String, Object> activationAccount(String confirmCode){
        return userAdminService.activationAccount(confirmCode);
    }

}
