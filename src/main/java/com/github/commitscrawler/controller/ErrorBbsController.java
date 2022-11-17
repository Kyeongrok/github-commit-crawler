package com.github.commitscrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorbbs")
public class ErrorBbsController {
    @GetMapping
    public String list(Model model) {
        return "bbs/list";
    }

}
