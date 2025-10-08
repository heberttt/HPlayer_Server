package com.Hebert.HPlayer.HMusic;


import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

public interface MusicDownloaderService {

    public DownloadMusicResult requestMusic(DownloadMusicRequest request);

}

