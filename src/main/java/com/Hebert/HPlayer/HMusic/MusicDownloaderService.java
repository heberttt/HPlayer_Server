package com.Hebert.HPlayer.HMusic;

import java.io.IOException;

public interface MusicDownloaderService {
    
    public void downloadMusic(String link) throws IOException, InterruptedException;

}

