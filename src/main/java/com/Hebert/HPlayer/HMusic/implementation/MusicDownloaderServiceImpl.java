package com.Hebert.HPlayer.HMusic.implementation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDownloadQueue;
import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

@Service
public class MusicDownloaderServiceImpl implements MusicDownloaderService{

    @Autowired
    private MusicRepository musicRepository;
    
    Queue<DownloadMusicRequest> musicDownloadQueue = new LinkedList<>();
    
    @Override
    public DownloadMusicResult downloadMusicProcess(DownloadMusicRequest request) throws IOException, InterruptedException {

        if (musicDownloadQueue.isEmpty()){
            musicDownloadQueue.add(request);
            downloadMusic();  // need thread?
        }else{
            musicDownloadQueue.add(request);
        }
        
        

        DownloadMusicResult result = new DownloadMusicResult();

        result.setSuccess(true);

        return result;

    }


    @Override
    public DownloadMusicResult downloadMusic() throws IOException, InterruptedException{

        DownloadMusicRequest request = musicDownloadQueue.peek();

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

            DownloadMusicResult result = new DownloadMusicResult();

            // Boolean success = false;


            try{
                musicRepository.addMusic(musicDO);


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


            return result;
            
        }


    }



    

    
    
}
