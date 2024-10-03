package com.Hebert.HPlayer.HMusic.implementation;

import java.io.IOException;
import java.util.List;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;

public class MusicDownloaderServiceImpl implements MusicDownloaderService {

    @Override
    public void downloadMusic(String link) throws IOException, InterruptedException{
        List<String> command = List.of("yt-dlp", "-x", "--audio-format", "mp3", "-o", link, link);
    }

    

    
    
}
