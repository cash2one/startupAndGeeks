package com.cmcc.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmcc.edu.entity.Admin;

@Controller
@RequestMapping("/message")
public class ClassringController extends PageController<Admin>{


    @RequestMapping
    public String list() {
        return "classring/index";
    }
}
