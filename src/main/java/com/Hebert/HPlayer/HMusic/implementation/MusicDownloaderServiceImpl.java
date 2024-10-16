package com.Hebert.HPlayer.HMusic.implementation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDownloadThread;
import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;

@Service
public class MusicDownloaderServiceImpl implements MusicDownloaderService{

    @Autowired
    MusicRepository musicRepository;

    
    private volatile Queue<DownloadMusicRequest> musicDownloadQueue = new LinkedList<>();

    
    
    @Override
    public ResponseEntity<DownloadMusicResult> requestMusic(DownloadMusicRequest request) throws IOException, InterruptedException {
        
        DownloadMusicResult result = new DownloadMusicResult();


        if (musicDownloadQueue.isEmpty()){
            musicDownloadQueue.add(request);
            System.out.println("add and start thread");
            downloadMusic();
            
        }else{
            musicDownloadQueue.add(request);
            System.out.println("only add");
        }
        

        result.setSuccess(true);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @Override
    public void downloadMusic() throws IOException, InterruptedException{

        DownloadMusicRequest request = musicDownloadQueue.peek();

        String currentDirectory = System.getProperty("user.dir");
        String cleanCode = YoutubeUtil.linkCodeGetter(YoutubeUtil.linkStandardization(request.getYoutubeLink())) + ".mp3";
        File checkFile = new File(currentDirectory + "/tempMusics/" + cleanCode);

        if (checkFile.exists()){
            System.out.println("mp3 exists");
        }

        MusicDownloadThread musicDownloadThread = new MusicDownloadThread(request, this, musicRepository);

        Thread downloadThread = new Thread(musicDownloadThread);

        downloadThread.start();

        // musicDownloadQueue.remove();

        // System.out.println("removing music from queue");
    }

    @Override
    public Boolean queueIsEmpty(){
        return musicDownloadQueue.isEmpty();
    }

    public void removeQueue(){
        musicDownloadQueue.remove();
    }

    

    
    
}
