package com.Hebert.HPlayer.HMusic.implementation;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class YoutubeDataApiConsumer {
    private static final String APPLICATION_NAME = "HPlayer";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String API_KEY = System.getenv("YOUTUBE_API_KEY");

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

        String[] result = new String[2];

        result[0] = video.getSnippet().getTitle();
        result[1] = video.getContentDetails().getDuration();

        return result;

    }
}