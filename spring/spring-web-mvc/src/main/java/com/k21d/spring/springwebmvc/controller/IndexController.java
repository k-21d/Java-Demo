package com.k21d.spring.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/goToIndex")
    public ModelAndView goToIndex(String msg){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",msg);
        mv.setViewName("index");
        return mv;
    }
}
