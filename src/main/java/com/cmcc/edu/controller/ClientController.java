package com.cmcc.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmcc.edu.entity.Admin;

@Controller
@RequestMapping("/client")
public class ClientController extends PageController<Admin>{


    @RequestMapping
    public String list() {
        return "client/index";
    }
}
