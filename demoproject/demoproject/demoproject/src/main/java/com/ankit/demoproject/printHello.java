package com.ankit.demoproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class printHello {

    @GetMapping("/")
    public String home() {
        return "Welcome to my Demo Project!";
    }
}