package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeiroController {
    @RequestMapping("/")
    public String olaMundo(){
        return "Ola Mundo";
    }
        
    @RequestMapping("/add")
    public Numero exercicio1(
        @RequestParam(value = "a", defaultValue = "0") String a, 
        @RequestParam(value = "b", defaultValue = "0") String b
    ){
        Integer num1 = 0, num2 = 0;

        try {
            num1 = Integer.parseInt(a);
        } catch (Exception e) {}

        try {
            num2 = Integer.parseInt(b);
        } catch (Exception e) {}

        return new Numero(num1+num2);
    }
}
