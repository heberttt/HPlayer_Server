package com.Hebert.HPlayer.HMusic;

import java.io.IOException;

public interface MusicDownloaderService {
    
    public Boolean downloadMusic(String link) throws IOException, InterruptedException;

}

