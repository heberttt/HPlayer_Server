package com.Hebert.HPlayer.HMusic.results;

import com.Hebert.HPlayer.HMusic.MusicDO;

public class DownloadMusicResult {
    
    private Boolean success;

    private MusicDO result;

    public MusicDO getResult() {
        return result;
    }

    public void setResult(MusicDO result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


}
