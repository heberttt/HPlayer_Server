package com.Hebert.HPlayer.YoutubeSearcher.Controller;

import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import com.Hebert.HPlayer.YoutubeSearcher.Result.SearchYoutubeVideosResult;
import com.Hebert.HPlayer.YoutubeSearcher.Service.YoutubeSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yt")
public class YoutubeSearchController {

    private final YoutubeSearchService youtubeSearchService;

    public YoutubeSearchController(YoutubeSearchService youtubeSearchService) {
        this.youtubeSearchService = youtubeSearchService;
    }

    @GetMapping
    public ResponseEntity<SearchYoutubeVideosResult> searchYoutubeVideos(@RequestParam String keyword){
        SearchYoutubeVideosResult result = youtubeSearchService.searchYoutubeVideos(keyword);

        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }
}
