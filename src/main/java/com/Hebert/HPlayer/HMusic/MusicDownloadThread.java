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

        String link = request.getYoutubeLink();

        String cleanLink = YoutubeUtil.linkCodeGetter(link);
        List<String> command = List.of("yt-dlp", "-x", "--audio-format", "mp3", "-o", cleanLink, link);
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);
        processBuilder.inheritIO();

        String currentDirectory = System.getProperty("user.dir");

        processBuilder.directory(new File(currentDirectory + "/tempMusics/"));

        Process process = processBuilder.start();

        process.waitFor();

        File downloadedFile = new File(currentDirectory + "/tempMusics/" + cleanLink + ".mp3");

        if(!downloadedFile.exists()){
            throw new IOException("Music file not found.");
        }else{
            MusicDO musicDO = new MusicDO();

            String[] musicData = YoutubeDataApiConsumer.getTitleAndDuration(YoutubeUtil.linkCodeGetter(link));

            String musicTitle = musicData[0];

            Integer musicDuration = YoutubeUtil.convertDurationIntoSeconds(musicData[1]);

            String musicLowThumbnailUrl = musicData[2];

            String musicHighThumbnailUrl = musicData[3];

            musicDO.setTitle(musicTitle);
            musicDO.setDuration(musicDuration);
            musicDO.setLink_code(cleanLink);
            musicDO.setLowThumbnailUrl(musicLowThumbnailUrl);
            musicDO.setHighThumbnailUrl(musicHighThumbnailUrl);
            musicDO.setMusicFile(YoutubeUtil.convertFileToByteArray(downloadedFile));



            try{
                System.out.println("Adding music to repository...");

                musicRepository.addMusic(musicDO);

                System.out.println("Music added");


                List<String> cleanCommand = List.of("rm", currentDirectory + "/tempMusics/" + cleanLink + ".mp3");
            
                ProcessBuilder cleanProcessBuilder = new ProcessBuilder(cleanCommand);

                cleanProcessBuilder.redirectErrorStream(true);
                cleanProcessBuilder.inheritIO();

                Process cleanProcess = cleanProcessBuilder.start();
                    
                cleanProcess.waitFor();

                // MusicDO musicDetails = musicRepository.queryMusicDetails(cleanLink).get();

                // success = true;

                // result.setSuccess(success);
                // result.setResult(musicDetails);
                
            }catch(Exception e){
                System.out.println(e);
            }

            if (!musicDownloaderService.queueIsEmpty()){
                musicDownloaderService.downloadMusic();
            }
            
            
        }
    }
}
