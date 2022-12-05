package com.github.commitscrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/progress/view")
public class StudentProgressViewController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}