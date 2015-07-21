package com.cmcc.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cmcc.edu.entity.Student;


@Controller
@RequestMapping("/phonebook")
public class PhonebookController extends PageController<Student>{
	
	
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "phonebook/student";
    }

}
