package com.Hebert.HPlayer.HMusic;


import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;
import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;

public interface MusicDownloaderService {

    public DownloadMusicResult requestMusic(DownloadMusicRequest request);

}

