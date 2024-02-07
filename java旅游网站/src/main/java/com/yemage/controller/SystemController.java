package com.yemage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    /**
     * 登陆页面跳转
     */
    @GetMapping("logintoadmin")
    public String logintoadmin() {
        return "logintoadmin";
    }

    @GetMapping("registrytoadmin")
    public String registrytoadmin() {
        return "registrytoadmin";
    }
    @GetMapping("logintouser")
    public String logintouser() {
        return "logintouser";
    }

    @GetMapping("registrytouser")
    public String registrytouser() {
        return "registrytouser";
    }


}
