package com.Hebert.HPlayer.HMusic;

import lombok.Data;

@Data
public class MusicDO {

    private String musicId;
    private String title;
    private String channelName;
    private Integer duration;
    

    public MusicDO(){};

    public MusicDO(String musicId, String title, String channelName, Integer duration){
        this.musicId = musicId;
        this.title = title;
        this.channelName = channelName;
        this.duration = duration;
    }


}
