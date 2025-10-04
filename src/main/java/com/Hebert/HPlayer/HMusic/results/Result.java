package com.Hebert.HPlayer.HMusic.results;

import lombok.Data;

@Data
public abstract class Result {
    private Boolean success = false;

    private Integer statusCode;

    private String message;

    public void setFail(String message, Integer statusCode){
        success = false;
        this.statusCode = statusCode;
        this.message = message;
    }

    public void setSuccess(String message, Integer statusCode){
        success = true;
        this.statusCode = statusCode;
        this.message = message;
    }
}
