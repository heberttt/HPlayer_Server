package com.Hebert.HPlayer.HMusic.implementation;

import java.io.IOException;


import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public class YoutubeDataApiConsumer {

    private static final String APPLICATION_NAME = "HPlayer";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String API_KEY = "AIzaSyCTETDRDZ_4NMafiYglWhYhy9jBvYspAmU";

    public static String[] getTitleAndDuration(String youtubeCode) throws IOException {
        
        YouTube youtubeService = new YouTube.Builder(new NetHttpTransport(), JSON_FACTORY, (HttpRequestInitializer) null)
                .setApplicationName(APPLICATION_NAME)
                .build();

        
        YouTube.Videos.List request = youtubeService.videos()
                .list("snippet,contentDetails");
        VideoListResponse response = request.setId(youtubeCode)
                .setKey(API_KEY)
                .execute();

        
        Video video = response.getItems().get(0);

        String[] result = new String[4];

        result[0] = video.getSnippet().getTitle();
        result[1] = video.getContentDetails().getDuration();
        result[2] = video.getSnippet().getThumbnails().getDefault().getUrl(); //low quality
        result[3] = video.getSnippet().getThumbnails().getHigh().getUrl();

        return result;

    }
}