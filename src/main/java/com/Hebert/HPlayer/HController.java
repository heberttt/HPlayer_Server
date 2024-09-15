package com.Hebert.HPlayer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HController {
    
    @GetMapping
    public String getHello(){
        return "hi";
    }

    @PostMapping
    public String postHello(
        @RequestBody String request
    ){
        return request;
    }

}
