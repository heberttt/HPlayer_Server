package com.Hebert.HPlayer;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.Hebert.HPlayer.YoutubeSearcher.Util.YoutubeDataApiConsumer;
import com.google.api.services.youtube.model.Video;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.SearchResult;

@RestController
@RequestMapping("/test")
public class HController {

    
    @GetMapping
    public List<Video> getHello() throws GoogleJsonResponseException, GeneralSecurityException, IOException{
        List<Video> videos = YoutubeDataApiConsumer.searchVideosWithStatistics("Bohemian rhapsody");

        System.out.println(videos.size());
        System.out.println(YoutubeDataApiConsumer.getTitleAndDuration("8jSBSS_Nk9A"));

        return videos;
    }

    @GetMapping("/currentDirectory")
    public String getCurrentWorkingDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        return "Current working directory: " + currentDirectory;
    }

    @GetMapping("/apiKey")
    public String getKey() {
        return System.getenv("YOUTUBE_API_KEY");
    }
    

    @PostMapping
    public String postHello(
        @RequestBody String request
    ){
        return request;
    }

}
