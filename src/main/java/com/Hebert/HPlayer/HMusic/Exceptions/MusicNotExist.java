package com.Hebert.HPlayer.HMusic.Exceptions;

import java.io.IOException;

public class MusicNotExist extends IOException{
    private String message;

    public MusicNotExist(String message){
        super(message);
        this.message = message;
    }

    @Override 
    public String getMessage(){
        return message;
    }

}
