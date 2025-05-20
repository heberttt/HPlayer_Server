package com.Hebert.HPlayer.HMusic.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDataService;
import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.MusicStreamService;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/music")
public class HMusicController {
    
    private final MusicStreamService musicStreamService;

    private final MusicDownloaderService musicDownloaderService;

    private final MusicDataService musicDataService;

    public HMusicController(MusicDownloaderService musicDownloaderService, MusicStreamService musicStreamService, MusicDataService musicDataService){
        this.musicDownloaderService = musicDownloaderService;
        this.musicStreamService = musicStreamService;
        this.musicDataService = musicDataService;
    }

    @PostMapping("/download")
    public ResponseEntity<DownloadMusicResult> downloadMusic(@RequestBody DownloadMusicRequest request) {
        

        return musicDownloaderService.requestMusic(request);
    }

    @GetMapping("/stream/{request}")
    public ResponseEntity<Resource> streamMusic(@PathVariable String request, @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
        
        return musicStreamService.streamMusic(request, rangeHeader);
        
    }

    @GetMapping("/data/{link}")
    public ResponseEntity<MusicDO> getMusicData(@PathVariable String link){
        return musicDataService.getMusicData(link);
    }

    @GetMapping("/data")
    public ResponseEntity<List<MusicDO>> getAllMusicData(){
        return musicDataService.getAllMusicData();
    }
    
    @GetMapping("/data/thumbnail/{youtubeCode}/low")
    public ResponseEntity<Resource> getMusicLowThumbnail(@PathVariable String youtubeCode) throws MalformedURLException{
        return musicDataService.getMusicLowThumbnail(youtubeCode);
    }

    @GetMapping("/data/thumbnail/{youtubeCode}/medium")
    public ResponseEntity<Resource> getMusicMediumThumbnail(@PathVariable String youtubeCode) throws MalformedURLException{
        return musicDataService.getMusicMediumThumbnail(youtubeCode);
    }

    @GetMapping("/data/thumbnail/{youtubeCode}/high")
    public ResponseEntity<Resource> getMusicHighThumbnail(@PathVariable String youtubeCode) throws MalformedURLException{
        return musicDataService.getMusicHighThumbnail(youtubeCode);
    }

    

}
