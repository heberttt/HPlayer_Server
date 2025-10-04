package com.Hebert.HPlayer.HMusic.requests;

import lombok.Data;

@Data
public class DownloadMusicWebhookEventRequest {
    private String musicId;
    private String title;
    private String channelName;
    private Integer duration;
}
