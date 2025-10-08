package com.Hebert.HPlayer.YoutubeSearcher.Model;

import lombok.Data;

@Data
public class SearchedVideoModel {
    private String videoTitle;
    private long views;
    private String channelTitle;
    private long createdAt;
    private String thumbnailHigh;
    private String thumbnailMedium;
    private String thumbnailLow;
    private int duration;
    private String youtubeCode;
}
