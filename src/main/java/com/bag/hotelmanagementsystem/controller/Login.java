package com.bag.hotelmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class Login {

    @GetMapping(value = "/getLogin")
    public String login() {
        return "login";
    }


}
