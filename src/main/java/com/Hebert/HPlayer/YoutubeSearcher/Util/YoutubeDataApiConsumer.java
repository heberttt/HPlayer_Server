package com.Hebert.HPlayer.YoutubeSearcher.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

public class YoutubeDataApiConsumer {

    private static final String APPLICATION_NAME = "HPlayer";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String API_KEY = System.getenv("YOUTUBE_API_KEY");

    private static final YouTube youtubeService = new YouTube.Builder(new NetHttpTransport(), JSON_FACTORY, (HttpRequestInitializer) null)
            .setApplicationName(APPLICATION_NAME)
            .build();

    public static List<String> getTitleAndDuration(String youtubeCode) throws IOException {
        

        YouTube youtubeService = new YouTube.Builder(new NetHttpTransport(), JSON_FACTORY, (HttpRequestInitializer) null)
                .setApplicationName(APPLICATION_NAME)
                .build();

        
        YouTube.Videos.List request = youtubeService.videos()
                .list("snippet,contentDetails");
        VideoListResponse response = request.setId(youtubeCode)
                .setKey(API_KEY)
                .execute();

        Video video = response.getItems().get(0);

        List<String> result = new ArrayList<>();

        result.add(video.getSnippet().getTitle());
        result.add(video.getContentDetails().getDuration());
        result.add(video.getSnippet().getChannelTitle());

        return result;

    }

    public static List<SearchResult> searchVideos(String searchedText) throws IOException{
        
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        
        SearchListResponse response = request
                .setKey(API_KEY)
                .setQ(searchedText)
                .setType("video")
                .setMaxResults(50L)
                .execute();

        return response.getItems();
    }

    public static List<Video> searchVideosWithStatistics(String searchedText) throws IOException {

        List<SearchResult> searchResults = searchVideos(searchedText);

        List<String> videoIds = new ArrayList<>();

        for (SearchResult result : searchResults){
            videoIds.add(result.getId().getVideoId());
        }

        YouTube.Videos.List videoRequest = youtubeService.videos()
                .list("snippet,statistics,contentDetails")
                .setId(String.join(",", videoIds))
                .setKey(API_KEY);

        VideoListResponse videoListResponse = videoRequest.execute();

        return videoListResponse.getItems();
    }

}