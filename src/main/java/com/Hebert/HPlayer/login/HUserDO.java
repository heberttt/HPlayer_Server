package com.Hebert.HPlayer.login;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;


public class HUserDO {
     
    @Id
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String role;




    public HUserDO(int id, String username, String password, String email, String role){
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }

    


}
