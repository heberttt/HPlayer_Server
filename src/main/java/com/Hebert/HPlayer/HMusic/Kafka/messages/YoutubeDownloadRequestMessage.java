package com.Hebert.HPlayer.HMusic.Kafka.messages;

import lombok.Data;

@Data
public class YoutubeDownloadRequestMessage {
    private String youtubeUrl;   
}
