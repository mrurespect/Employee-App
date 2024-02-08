package com.mrurespect.employeeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/access-denied")
    public String denied(){
        return "denied" ;
    }
}