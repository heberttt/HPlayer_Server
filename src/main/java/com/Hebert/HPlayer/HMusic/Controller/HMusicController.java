package com.Hebert.HPlayer.HMusic.Controller;

import java.net.MalformedURLException;
import java.util.List;

import com.Hebert.HPlayer.HMusic.*;
import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import com.Hebert.HPlayer.HMusic.results.GetPresignedThumbnailUrlResult;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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
    public ResponseEntity<DownloadMusicResult> requestMusic(@RequestBody DownloadMusicRequest request) {
        DownloadMusicResult result = musicDownloaderService.requestMusic(request);

        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }

    @GetMapping("/{youtubeCode}/url")
    public ResponseEntity<GetPresignedMusicUrlResult> getPresignedMusicUrl(@PathVariable String youtubeCode){
        GetPresignedMusicUrlResult result = musicDataService.getMusicPresignedUrl(youtubeCode);

        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatusCode()));
    }

//    @GetMapping("/streamMusic/{request}")
//    public ResponseEntity<Resource> streamMusic(@PathVariable String request, @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
//
//        return musicStreamService.streamMusic(request, rangeHeader);
//
//    }

    @GetMapping("/data/{link}")
    public ResponseEntity<MusicDO> getMusicData(@PathVariable String link){
        return musicDataService.getMusicData(link);
    }

    @GetMapping("/data")
    public ResponseEntity<List<MusicDO>> getAllMusicData(){
        return musicDataService.getAllMusicData();
    }

//    @GetMapping("/data/thumbnail/{musicId}/{quality}")
//    public ResponseEntity<GetPresignedThumbnailUrlResult> getMusicLowThumbnail(@PathVariable String musicId, @PathVariable String quality) throws MalformedURLException{
//        GetPresignedThumbnailUrlResult result;
//
//        switch (quality){
//            case "low":
//                result = musicDataService.getThumbnailPresignedUrl(musicId, ThumbnailQuality.LOW);
//                break;
//            case "medium":
//                result = musicDataService.getThumbnailPresignedUrl(musicId, ThumbnailQuality.MEDIUM);
//                break;
//            case "high":
//                result = musicDataService.getThumbnailPresignedUrl(musicId, ThumbnailQuality.HIGH);
//                break;
//            default:
//                return new ResponseEntity<>(HttpStatusCode.valueOf(404));
//        }
//
//        return new ResponseEntity<>(result, HttpStatusCode.valueOf(result.getStatusCode()));
//
//    }

}
