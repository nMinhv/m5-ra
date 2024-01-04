package com.ra.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HandleController {
    @RequestMapping("/forbidden")
    public String forbiddenPage(){
        return "403";
    }
}
