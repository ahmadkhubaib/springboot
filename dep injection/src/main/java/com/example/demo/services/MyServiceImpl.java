package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    public String sayHello() {
        return "Hello from Service implementation";
    }

}
