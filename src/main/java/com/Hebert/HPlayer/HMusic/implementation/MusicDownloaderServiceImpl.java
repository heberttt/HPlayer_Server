package com.Hebert.HPlayer.HMusic.implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.MusicRepository;

public class MusicDownloaderServiceImpl implements MusicDownloaderService{

    @Autowired
    private MusicRepository musicRepository;

    @Override
    public void downloadMusic(String link) throws IOException, InterruptedException{
        List<String> command = List.of("yt-dlp", "-x", "--audio-format", "mp3", "-o", link, link);
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);
        processBuilder.inheritIO();

        Process process = processBuilder.start();

        process.waitFor();

        File downloadedFile = new File("link.mp3");

        if(!downloadedFile.exists()){
            throw new IOException("Music file not found.");
        }else{
            MusicDO musicDO = new MusicDO();

            String[] musicData = YoutubeDataApiConsumer.getTitleAndDuration(link);

            String musicTitle = musicData[0];

            String musicDuration = musicData[1];

            //musicRepository.addMusic();
        }


    }

    

    
    
}
