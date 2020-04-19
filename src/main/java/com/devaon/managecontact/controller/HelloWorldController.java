package com.devaon.managecontact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/api/helloworld")
    public String helloWord(){
        return "HelloWorld";
    }
}
