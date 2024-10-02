package com.Hebert.HPlayer.HMusic;



public class MusicDO {

    private String link_code;
    private String title;
    private Integer duration;
    private byte[] musicFile; //change to file;

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
    public byte[] getMusicFile() {
        return musicFile;
    }
    public void setMusicFile(byte[] musicFile) {
        this.musicFile = musicFile;
    }



}
