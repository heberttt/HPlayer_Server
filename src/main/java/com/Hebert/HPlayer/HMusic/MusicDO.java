package com.Hebert.HPlayer.HMusic;

public class MusicDO {

    private String link_code;
    private String title;
    private String channelName;
    private Integer duration;
    

    public MusicDO(){};

    public MusicDO(String link_code, String title, String channelName, Integer duration){
        this.link_code = link_code;
        this.title = title;
        this.channelName = channelName;
        this.duration = duration;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLink_code() {
        return link_code;
    }
    public void setLink_code(String link_code) {
        this.link_code = link_code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    



}
