package com.Hebert.HPlayer.HMusic;


import java.io.File;
import java.io.IOException;
import java.util.List;

import com.Hebert.HPlayer.HMusic.implementation.YoutubeDataApiConsumer;
import com.Hebert.HPlayer.HMusic.implementation.YoutubeUtil;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;


public class MusicDownloadThread implements Runnable{

    private DownloadMusicRequest request;

    @Override
    public void run() {
        try {
            downloadMusic(this.request);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    

    public MusicDownloadThread(DownloadMusicRequest request, MusicDownloaderService musicDownloaderService, MusicRepository musicRepository){
        this.request = request;
        this.musicDownloaderService = musicDownloaderService;
        this.musicRepository = musicRepository;
        
    }
    
    private final MusicRepository musicRepository;

    private final MusicDownloaderService musicDownloaderService;
    
    public void downloadMusic(DownloadMusicRequest request) throws IOException, InterruptedException{

        String link = YoutubeUtil.linkStandardization(request.getYoutubeLink());

        String cleanLink = YoutubeUtil.linkCodeGetter(link);

        //check if exist

        String currentDirectory = System.getProperty("user.dir");


        List<String> command = List.of("yt-dlp", "-x", "--audio-format", "mp3", "-o", cleanLink, link);
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);
        processBuilder.inheritIO();

        File logFile = new File(currentDirectory + "/logs/yt-dlp_output.txt");
        processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));

        processBuilder.directory(new File(currentDirectory + "/tempMusics/"));

        Process process = processBuilder.start();

        process.waitFor();

        File downloadedFile = new File(currentDirectory + "/tempMusics/" + cleanLink + ".mp3");

        if(!downloadedFile.exists()){
            throw new IOException("Music file not found.");
        }else{
            MusicDO musicDO = new MusicDO();

            List<String> musicData = YoutubeDataApiConsumer.getTitleAndDuration(YoutubeUtil.linkCodeGetter(link));

            String musicTitle = musicData.get(0);

            Integer musicDuration =  YoutubeUtil.convertDurationIntoSeconds(musicData.get(1));
            
            String musicChannelName = musicData.get(2);

            musicDO.setTitle(musicTitle);
            musicDO.setDuration(musicDuration);
            musicDO.setLink_code(cleanLink);
            musicDO.setChannelName(musicChannelName);

            YoutubeUtil.downloadThumbnail(cleanLink);

            try{
                System.out.println("Adding music to repository...");

                musicRepository.addMusic(musicDO);

                System.out.println("Music added");
                
            }catch(Exception e){
                System.out.println(e);
            }

            musicDownloaderService.removeQueue();
            System.out.println("removed queue");

            if (!musicDownloaderService.queueIsEmpty()){
                musicDownloaderService.downloadMusic();
            }
            
            
        }
    }
}
