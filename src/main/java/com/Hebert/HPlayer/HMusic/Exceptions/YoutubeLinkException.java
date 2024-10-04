package com.Hebert.HPlayer.HMusic.Exceptions;

public class YoutubeLinkException extends RuntimeException {

    private String message;

    public YoutubeLinkException(String message){
        super(message);
        this.message = message;
    }

    @Override 
    public String getMessage(){
        return message;
    }

    
}
