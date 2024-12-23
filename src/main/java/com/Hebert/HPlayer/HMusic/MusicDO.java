package com.Hebert.HPlayer.HMusic;



public class MusicDO {

    private String link_code;
    private String title;
    private Integer duration;
    private String lowThumbnailUrl;
    private String mediumThumbnailUrl;
    private String highThumbnailUrl;
    

    public MusicDO(){};

    public MusicDO(String link_code, String title, Integer duration, String lowThumbnailUrl, String highThumbnailUrl){
        this.link_code = link_code;
        this.title = title;
        this.duration = duration;
        this.lowThumbnailUrl = lowThumbnailUrl;
        this.highThumbnailUrl = highThumbnailUrl;
        this.mediumThumbnailUrl = lowThumbnailUrl.replace("default.jpg", "mqdefault.jpg");
    }

    public String getMediumThumbnailUrl(){
        return mediumThumbnailUrl;
    }

    public String getLowThumbnailUrl() {
        return lowThumbnailUrl;
    }

    public void setLowThumbnailUrl(String lowThumbnailUrl) {
        this.lowThumbnailUrl = lowThumbnailUrl;
        this.mediumThumbnailUrl = lowThumbnailUrl.replace("default.jpg", "mqdefault.jpg");
    }

    public String getHighThumbnailUrl() {
        return highThumbnailUrl;
    }

    public void setHighThumbnailUrl(String highThumbnailUrl) {
        this.highThumbnailUrl = highThumbnailUrl;
        this.mediumThumbnailUrl = highThumbnailUrl.replace("hqdefault.jpg", "mqdefault.jpg");
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
