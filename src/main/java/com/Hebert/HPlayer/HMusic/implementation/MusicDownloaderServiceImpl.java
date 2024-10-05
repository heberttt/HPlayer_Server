package com.Hebert.HPlayer.HMusic.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.MusicRepository;

@Service
public class MusicDownloaderServiceImpl implements MusicDownloaderService{

    @Autowired
    private MusicRepository musicRepository;

    @Override
    public Boolean downloadMusic(String link) throws IOException, InterruptedException{
        String cleanLink = YoutubeUtil.linkCodeGetter(link);
        List<String> command = List.of("yt-dlp", "-x", "--audio-format", "mp3", "-o", cleanLink, link);
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);
        processBuilder.inheritIO();
        processBuilder.directory(new File("/home/himagi/Documents/development/HPlayer/tempMusics/"));

        Process process = processBuilder.start();

        process.waitFor();

        File downloadedFile = new File("/home/himagi/Documents/development/HPlayer/tempMusics/" + cleanLink + ".mp3");

        if(!downloadedFile.exists()){
            throw new IOException("Music file not found.");
        }else{
            MusicDO musicDO = new MusicDO();

            String[] musicData = YoutubeDataApiConsumer.getTitleAndDuration(YoutubeUtil.linkCodeGetter(link));

            String musicTitle = musicData[0];

            Integer musicDuration = YoutubeUtil.convertDurationIntoSeconds(musicData[1]);

            musicDO.setTitle(musicTitle);
            musicDO.setDuration(musicDuration);
            musicDO.setLink_code(link);
            musicDO.setMusicFile(YoutubeUtil.convertFileToByteArray(downloadedFile));

            try{
                musicRepository.addMusic(musicDO);


                List<String> cleanCommand = List.of("rm", "/home/himagi/Documents/development/HPlayer/tempMusics/" + cleanLink + ".mp3");
        
                ProcessBuilder cleanProcessBuilder = new ProcessBuilder(cleanCommand);

                cleanProcessBuilder.redirectErrorStream(true);
                cleanProcessBuilder.inheritIO();

                Process cleanProcess = cleanProcessBuilder.start();
                
                cleanProcess.waitFor();


            }catch(Exception e){
                System.out.println(e.toString());
                return false;
            }
            


            return true;
        }


    }

    

    
    
}
