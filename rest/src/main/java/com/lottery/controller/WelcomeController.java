package com.lottery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangyaohai on 2017/10/24.
 */
@RestController
public class WelcomeController extends BaseController{
    @RequestMapping("")
    public String welcome(){
        return "welcome to lottery site!";
    }
}
