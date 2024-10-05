package com.Hebert.HPlayer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hebert.HPlayer.HMusic.implementation.YoutubeDataApiConsumer;
import com.Hebert.HPlayer.login.HUserDO;
import com.Hebert.HPlayer.login.HUserRepositoryImpl;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

@RestController
@RequestMapping("/test")
public class HController {

    private final HUserRepositoryImpl hUserRepository;

    public HController(HUserRepositoryImpl hUserRepository){
        this.hUserRepository = hUserRepository;
    }
    
    @GetMapping
    public List<String> getHello() throws GoogleJsonResponseException, GeneralSecurityException, IOException{
        return Arrays.asList(YoutubeDataApiConsumer.getTitleAndDuration("8jSBSS_Nk9A"));
    }

    @PostMapping
    public String postHello(
        @RequestBody String request
    ){
        return request;
    }

    @GetMapping("/user/email")
    public HUserDO getUserByEmail(@RequestParam String email){
        Optional<HUserDO> result = hUserRepository.findByEmail(email);
        if (result == null){
            throw new RuntimeException("no user found");
        }

        return result.get();
    }

}
