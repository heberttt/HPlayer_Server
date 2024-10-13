package com.Hebert.HPlayer.HMusic.results;

public class DownloadMusicResult {
    
    private Boolean success = false;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


}
