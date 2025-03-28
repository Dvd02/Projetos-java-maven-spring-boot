package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {
    @RequestMapping("/")
    public String olaMundo(){
        return "Ola Mundo";
    }
    
    @RequestMapping("/teste")
    public String teste(){
        return "testado";
    }
}
