package com.chuanggu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuanggu.app.entity.Admin;

@Controller
@RequestMapping("/points")
public class PointsController extends PageController<Admin>{

	@RequestMapping
    public String list() {
        return "points/index";
    }
	
	@RequestMapping("index")
    public String pointsIndex() {
        return "points/index";
    }
    
    @RequestMapping("exchangeMy")
    public String exchangeMy() {
        return "points/exchangeMy";
    }
    
    @RequestMapping("help")
    public String help() {
        return "points/help";
    }
    
    @RequestMapping("pointsDetail")
    public String pointsDetail() {
        return "points/pointsDetail";
    }
    
    @RequestMapping("pointsDetail/month")
    public String month() {
        return "points/month";
    }
}
