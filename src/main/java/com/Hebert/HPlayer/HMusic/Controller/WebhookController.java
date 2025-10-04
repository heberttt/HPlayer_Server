package com.Hebert.HPlayer.HMusic.Controller;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDataService;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicWebhookEventRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final MusicDataService musicDataService;

    public WebhookController(MusicDataService musicDataService) {
        this.musicDataService = musicDataService;
    }

    @PostMapping("/download-event")
    public ResponseEntity<Void> handleDownloadEventWebhook(@RequestBody DownloadMusicWebhookEventRequest request){

        System.out.println("Webhook received: " + request.getMusicId());

        MusicDO musicDO = new MusicDO(request.getMusicId(), request.getTitle(), request.getChannelName(), request.getDuration());

        Boolean result = musicDataService.addMusic(musicDO);

        return new ResponseEntity<>(HttpStatusCode.valueOf(result ? 200 : 500));
    }
}
