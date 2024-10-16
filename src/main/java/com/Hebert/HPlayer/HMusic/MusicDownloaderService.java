package com.Hebert.HPlayer.HMusic;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

public interface MusicDownloaderService {

    public ResponseEntity<DownloadMusicResult> requestMusic(DownloadMusicRequest request) throws IOException, InterruptedException;
    
    public void downloadMusic() throws IOException, InterruptedException;

    public Boolean queueIsEmpty();

    public void removeQueue();
}

