package com.chuanggu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuanggu.app.entity.Admin;

@Controller
@RequestMapping("/client")
public class ClientController extends PageController<Admin>{


    @RequestMapping
    public String list() {
        return "client/index";
    }
}
