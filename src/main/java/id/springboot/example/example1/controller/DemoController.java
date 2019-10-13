package id.springboot.example.example1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "HelloWorld 123 234 456 567";
    }

}
