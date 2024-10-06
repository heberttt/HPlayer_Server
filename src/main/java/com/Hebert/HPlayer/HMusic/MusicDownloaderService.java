package com.Hebert.HPlayer.HMusic;

import java.io.IOException;
import java.util.Optional;

import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

public interface MusicDownloaderService {
    
    public DownloadMusicResult downloadMusic(String link) throws IOException, InterruptedException;

}

