package com.Hebert.HPlayer.HMusic.implementation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
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

    
    private Queue<DownloadMusicRequest> musicDownloadQueue = new LinkedList<>();
    
    @Override
    public DownloadMusicResult requestMusic(DownloadMusicRequest request) throws IOException, InterruptedException {

        if (musicDownloadQueue.isEmpty()){
            musicDownloadQueue.add(request);
            downloadMusic();
        }else{
            musicDownloadQueue.add(request);
        }
        
        

        DownloadMusicResult result = new DownloadMusicResult();

        result.setSuccess(true);

        return result;

    }


    @Override
    public void downloadMusic() throws IOException, InterruptedException{

        DownloadMusicRequest request = musicDownloadQueue.peek();

        MusicDownloadThread musicDownloadThread = new MusicDownloadThread(request, this, musicRepository);

        Thread downloadThread = new Thread(musicDownloadThread);

        downloadThread.start();

        musicDownloadQueue.remove();

        System.out.println("removing music from queue");
    }

    @Override
    public Boolean queueIsEmpty(){
        return musicDownloadQueue.isEmpty();
    }


    

    
    
}
