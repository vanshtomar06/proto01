package com.prototype1.proto1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/req/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/req/login")
    public String loginPage(){
        return "log-in";
    }

    @GetMapping("/map")
    public String getMap(){
        return "map";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
}
