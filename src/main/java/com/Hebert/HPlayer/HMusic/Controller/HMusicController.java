package com.Hebert.HPlayer.HMusic.Controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;


@RestController
@RequestMapping("/music")
public class HMusicController {
    
    private final MusicDownloaderService musicDownloaderService;

    public HMusicController(MusicDownloaderService musicDownloaderService){
        this.musicDownloaderService = musicDownloaderService;
    }

    @PostMapping("/downloadMusic")
    public ResponseEntity<DownloadMusicResult> downloadMusic(@RequestBody DownloadMusicRequest request) throws IOException, InterruptedException {
        
        DownloadMusicResult result = musicDownloaderService.downloadMusic(request.getYoutubeLink());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    

}
