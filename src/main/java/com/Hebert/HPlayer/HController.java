package com.Hebert.HPlayer;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hebert.HPlayer.login.HUserDO;
import com.Hebert.HPlayer.login.HUserRepositoryImpl;

@RestController
@RequestMapping("/test")
public class HController {

    private final HUserRepositoryImpl hUserRepository;

    HController(HUserRepositoryImpl hUserRepository){
        this.hUserRepository = hUserRepository;
    }
    
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

    @GetMapping("/user/email")
    public HUserDO getUserByEmail(@RequestBody String email){
        Optional<HUserDO> result = hUserRepository.findByEmail(email);
        if (result == null){
            throw new RuntimeException("no user found");
        }

        return result.get();
    }

}
