package com.Hebert.HPlayer.HMusic;

import java.io.IOException;
import java.util.Optional;

import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

public interface MusicDownloaderService {

    public DownloadMusicResult downloadMusicProcess(DownloadMusicRequest request) throws IOException, InterruptedException;
    
    public DownloadMusicResult downloadMusic() throws IOException, InterruptedException;

}

