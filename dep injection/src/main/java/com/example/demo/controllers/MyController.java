package com.example.demo.controllers;

import org.springframework.stereotype.Controller;

import com.example.demo.services.MyService;

@Controller
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    public String greet(){
        return myService.sayHello();
    }

}
